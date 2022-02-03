package com.pss.data.repository.remote.datasourceimpl

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.pss.data.remote.api.LoveCalculatorApi
import com.pss.data.remote.model.DataLoveResponse
import com.pss.data.repository.remote.datasource.MainDataSource
import com.pss.data.utils.base.BaseDataSource
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

    override fun getStatistics(): Task<DataSnapshot> {
        return firebaseRtdb.reference.child("statistics").get()
    }

    override fun setStatistics(plusResult: Int): Task<Void> {
        return firebaseRtdb.reference.child("statistics").setValue(plusResult)
    }
}