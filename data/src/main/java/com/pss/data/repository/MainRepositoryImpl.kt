package com.pss.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.pss.data.mapper.MainMapper
import com.pss.data.repository.remote.datasource.MainDataSource
import com.pss.domain.model.DomainLoveResponse
import com.pss.domain.model.DomainScore
import com.pss.domain.model.GetFirebaseResponse
import com.pss.domain.model.SetFirebaseResponse
import com.pss.domain.repository.MainRepository
import com.pss.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource
    ) : MainRepository {

    override suspend fun checkLoveCalculator(remoteErrorEmitter: RemoteErrorEmitter, host : String, key : String, mName : String, wName : String): DomainLoveResponse? {
        return MainMapper.loveMapper(mainDataSource.checkLoveCalculator(remoteErrorEmitter = remoteErrorEmitter, host = host, key = key, mName = mName, wName = wName))
    }

    override suspend fun getStatistics(): GetFirebaseResponse<String> {
        return mainDataSource.getStatistics()
    }

    override suspend fun setStatistics(plusResult: Int): SetFirebaseResponse {
        return mainDataSource.setStatistics(plusResult)
    }

    override suspend fun setScore(score: DomainScore): SetFirebaseResponse {
        return mainDataSource.setScore(MainMapper.scoreMapper(score))
    }

    override suspend fun getScore(): GetFirebaseResponse<List<DomainScore>> {
        return mainDataSource.getScore()
    }
}