package com.pss.data.repository.remote.datasourceimpl

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.pss.data.mapper.FirebaseMapper.toResultString
import com.pss.data.remote.api.LoveCalculatorApi
import com.pss.data.remote.model.DataLoveResponse
import com.pss.data.remote.model.DataScore
import com.pss.data.repository.remote.datasource.MainDataSource
import com.pss.data.utils.base.BaseDataSource
import com.pss.domain.model.DomainScore
import com.pss.domain.model.GetFirebaseResponse
import com.pss.domain.model.SetFirebaseResponse
import com.pss.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class MainDataSourceImpl @Inject constructor(
    private val loveCalculatorApi: LoveCalculatorApi,
    private val firebaseRtdb: FirebaseDatabase,
    private val fireStore: FirebaseFirestore
) : BaseDataSource(), MainDataSource {

    override suspend fun checkLoveCalculator(remoteErrorEmitter: RemoteErrorEmitter, host : String, key : String, mName : String, wName : String): DataLoveResponse? {
        return safeApiCall(remoteErrorEmitter){
            loveCalculatorApi.getPercentage(host = host, key = key, fName = wName, sName = mName)
        }?.body()
    }

    override suspend fun getStatistics(): GetFirebaseResponse<String> {
        return safeGetFirebaseRTDBCall{
            firebaseRtdb.reference.child("statistics").get()
        }.toResultString()
    }

    override suspend fun setStatistics(plusResult: Int): SetFirebaseResponse {
        return safeSetFirebaseRTDBCall{
            firebaseRtdb.reference.child("statistics").setValue(plusResult)
        }
    }

    override suspend fun setScore(score: DataScore): SetFirebaseResponse {
        return safeSetFirebaseRTDBCall{
            fireStore.collection("score").document().set(score)
        }
    }

    override suspend fun getScore(): GetFirebaseResponse<List<DomainScore>> {
        return safeSetFireStoreCall {
            fireStore.collection("score").orderBy("date", Query.Direction.DESCENDING).get()
        }
    }
}