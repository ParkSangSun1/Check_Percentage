package com.pss.data.mapper

import com.pss.data.remote.model.DataLoveResponse
import com.pss.domain.model.DomainLoveResponse
import com.pss.domain.utils.ErrorType
import com.pss.domain.utils.RemoteErrorEmitter

object MainMapper {

    fun loveMapper(
        remoteErrorEmitter: RemoteErrorEmitter,
        dataResponse: DataLoveResponse?
    ): DomainLoveResponse? {
        return if (dataResponse != null) {
            DomainLoveResponse(
                fname = dataResponse.fname,
                percentage = dataResponse.percentage,
                result = dataResponse.result,
                sname = dataResponse.sname
            )
        } else dataResponse
    }
}