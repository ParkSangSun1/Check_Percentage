package com.pss.domain.repository

import com.pss.domain.model.DomainLoveResponse
import com.pss.domain.model.DomainScore
import com.pss.domain.model.GetFirebaseResponse
import com.pss.domain.model.SetFirebaseResponse
import com.pss.domain.utils.RemoteErrorEmitter

interface MainRepository {
    suspend fun checkLoveCalculator(remoteErrorEmitter: RemoteErrorEmitter, host : String, key : String, mName : String, wName : String) : DomainLoveResponse?

    suspend fun getStatistics() : GetFirebaseResponse<String>

    suspend fun setStatistics(plusResult : Int) : SetFirebaseResponse

    suspend fun setScore(score: DomainScore) : SetFirebaseResponse

    suspend fun getScore(): GetFirebaseResponse<List<DomainScore>>
}