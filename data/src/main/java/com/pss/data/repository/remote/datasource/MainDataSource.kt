package com.pss.data.repository.remote.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.pss.data.remote.model.DataLoveResponse
import com.pss.domain.utils.RemoteErrorEmitter

interface MainDataSource {
    suspend fun checkLoveCalculator(remoteErrorEmitter: RemoteErrorEmitter, host : String, key : String, mName : String, wName : String) : DataLoveResponse?

    fun getStatistics() : Task<DataSnapshot>
}