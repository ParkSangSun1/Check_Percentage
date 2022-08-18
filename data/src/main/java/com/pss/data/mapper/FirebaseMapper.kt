package com.pss.data.mapper

import com.google.firebase.database.DataSnapshot
import com.pss.domain.model.GetFirebaseResponse

object FirebaseMapper {

    fun GetFirebaseResponse<DataSnapshot>.toResultString(): GetFirebaseResponse<String> {
        return GetFirebaseResponse(
            result = this.result?.value.toString(),
            state = this.state
        )
    }
}