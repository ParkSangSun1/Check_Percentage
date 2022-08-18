package com.pss.data.repository.remote.datasourceimpl

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.pss.data.mapper.FirebaseMapper.toResultString
import com.pss.data.repository.remote.datasource.SplashDataSource
import com.pss.data.utils.base.BaseDataSource
import com.pss.domain.model.GetFirebaseResponse
import javax.inject.Inject

class SplashDataSourceImpl @Inject constructor(
    private val firebaseRtdb: FirebaseDatabase,
    private val fireStore: FirebaseFirestore
) : SplashDataSource, BaseDataSource() {
    override suspend fun checkAppVersion() = safeGetFirebaseRTDBCall{
        firebaseRtdb.reference.child("appVersion").get()
    }.toResultString()
}