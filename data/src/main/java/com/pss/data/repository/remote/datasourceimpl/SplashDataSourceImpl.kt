package com.pss.data.repository.remote.datasourceimpl

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SplashDataSourceImpl @Inject constructor(
    private val firebaseRtdb: FirebaseDatabase,
    private val fireStore: FirebaseFirestore
) {
    fun checkAppVersion() = firebaseRtdb.reference.child("version").get()
}