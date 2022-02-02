package com.pss.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.pss.data.mapper.MainMapper
import com.pss.data.repository.remote.datasource.MainDataSource
import com.pss.data.repository.remote.datasource.SplashDataSource
import com.pss.domain.model.DomainLoveResponse
import com.pss.domain.repository.MainRepository
import com.pss.domain.repository.SplashRepository
import com.pss.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(
    private val splashDataSource: SplashDataSource
) : SplashRepository {

    override fun checkAppVersion(): Task<DataSnapshot> {
        return splashDataSource.checkAppVersion()
    }
}