package com.pss.data.repository

import com.pss.data.mapper.MainMapper
import com.pss.data.repository.remote.datasource.MainDataSource
import com.pss.data.utils.base.BaseRepository
import com.pss.domain.model.DomainLoveResponse
import com.pss.domain.repository.MainRepository
import com.pss.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource
    ) : MainRepository {

    override suspend fun checkLoveCalculator(remoteErrorEmitter: RemoteErrorEmitter, host : String, key : String, mName : String, wName : String): DomainLoveResponse? {
        return MainMapper.loveMapper(remoteErrorEmitter,mainDataSource.checkLoveCalculator(remoteErrorEmitter = remoteErrorEmitter, host = host, key = key, mName = mName, wName = wName))
    }
}