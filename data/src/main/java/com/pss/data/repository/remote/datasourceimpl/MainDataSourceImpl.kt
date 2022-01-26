package com.pss.data.repository.remote.datasourceimpl

import com.pss.data.remote.api.LoveCalculatorApi
import com.pss.data.remote.model.DataLoveResponse
import com.pss.data.repository.remote.datasource.MainDataSource
import com.pss.data.utils.base.BaseRepository
import com.pss.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class MainDataSourceImpl @Inject constructor(
    private val loveCalculatorApi: LoveCalculatorApi
) : BaseRepository(), MainDataSource {

    override suspend fun checkLoveCalculator(remoteErrorEmitter: RemoteErrorEmitter, host : String, key : String, mName : String, wName : String): DataLoveResponse? {
        return safeApiCall(remoteErrorEmitter){
            loveCalculatorApi.getPercentage(host = host, key = key, fName = wName, sName = mName)
        }?.body()
    }
}