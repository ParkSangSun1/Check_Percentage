package com.pss.domain.repository

import com.pss.domain.model.DomainLoveResponse
import com.pss.domain.utils.RemoteErrorEmitter
import retrofit2.Response

interface MainRepository {
    suspend fun checkLoveCalculator(remoteErrorEmitter: RemoteErrorEmitter, host : String, key : String, mName : String, wName : String) : DomainLoveResponse?
}