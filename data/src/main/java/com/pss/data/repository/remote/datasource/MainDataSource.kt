package com.pss.data.repository.remote.datasource

import com.pss.data.remote.model.DataLoveResponse
import com.pss.domain.utils.RemoteErrorEmitter

interface MainDataSource {
    suspend fun checkLoveCalculator(remoteErrorEmitter: RemoteErrorEmitter, host : String, key : String, mName : String, wName : String) : DataLoveResponse
}