package com.pss.domain.repository

import com.pss.domain.model.GetFirebaseResponse

interface SplashRepository {
    suspend fun checkAppVersion()  : GetFirebaseResponse<String>
}