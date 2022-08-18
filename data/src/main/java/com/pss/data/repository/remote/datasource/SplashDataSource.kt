package com.pss.data.repository.remote.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.pss.domain.model.DomainScore
import com.pss.domain.model.GetFirebaseResponse

interface SplashDataSource {
    suspend fun checkAppVersion() : GetFirebaseResponse<String>
}