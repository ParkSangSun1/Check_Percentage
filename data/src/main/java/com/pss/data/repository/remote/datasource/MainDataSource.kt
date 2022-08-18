package com.pss.data.repository.remote.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.pss.data.remote.model.DataLoveResponse
import com.pss.data.remote.model.DataScore
import com.pss.domain.model.DomainScore
import com.pss.domain.model.GetFirebaseResponse
import com.pss.domain.model.SetFirebaseResponse
import com.pss.domain.utils.RemoteErrorEmitter

interface MainDataSource {
    suspend fun checkLoveCalculator(
        remoteErrorEmitter: RemoteErrorEmitter,
        host: String,
        key: String,
        mName: String,
        wName: String
    ): DataLoveResponse?

    suspend fun getStatistics(): GetFirebaseResponse<String>

    suspend fun setStatistics(plusResult: Int): SetFirebaseResponse

    suspend fun setScore(score: DataScore): SetFirebaseResponse

    suspend fun getScore(): GetFirebaseResponse<List<DomainScore>>
}