package com.pss.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.pss.data.mapper.MainMapper
import com.pss.data.remote.model.DataScore
import com.pss.data.repository.remote.datasource.MainDataSource
import com.pss.domain.model.DomainLoveResponse
import com.pss.domain.model.DomainScore
import com.pss.domain.repository.MainRepository
import com.pss.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource
    ) : MainRepository {

    override suspend fun checkLoveCalculator(remoteErrorEmitter: RemoteErrorEmitter, host : String, key : String, mName : String, wName : String): DomainLoveResponse? {
        return MainMapper.loveMapper(mainDataSource.checkLoveCalculator(remoteErrorEmitter = remoteErrorEmitter, host = host, key = key, mName = mName, wName = wName))
    }

    override fun getStatistics(): Task<DataSnapshot> {
        return mainDataSource.getStatistics()
    }

    override fun setStatistics(plusResult: Int): Task<Void> {
        return mainDataSource.setStatistics(plusResult)
    }

    override fun setScore(score: DomainScore): Task<Void> {
        return mainDataSource.setScore(MainMapper.scoreMapper(score))
    }
}