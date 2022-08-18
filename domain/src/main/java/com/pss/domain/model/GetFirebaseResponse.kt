package com.pss.domain.model

import com.pss.domain.utils.FirebaseState

data class GetFirebaseResponse<T>(
    val state : FirebaseState,
    val result : T?
)

data class SetFirebaseResponse(
    val state : FirebaseState
)