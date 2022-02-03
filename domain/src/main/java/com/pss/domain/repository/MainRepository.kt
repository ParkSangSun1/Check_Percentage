package com.pss.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.pss.domain.model.DomainLoveResponse
import com.pss.domain.utils.RemoteErrorEmitter
import retrofit2.Response

interface MainRepository {
    suspend fun checkLoveCalculator(remoteErrorEmitter: RemoteErrorEmitter, host : String, key : String, mName : String, wName : String) : DomainLoveResponse?

    fun getStatistics() : Task<DataSnapshot>

    fun setStatistics(plusResult : Int) : Task<Void>
}