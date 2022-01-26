package com.pss.data.mapper

import com.pss.data.remote.model.DataLoveResponse
import com.pss.domain.model.DomainLoveResponse

object MainMapper {

    fun loveMapper(dataResponse: DataLoveResponse): DomainLoveResponse {
        return DomainLoveResponse(
            fname = dataResponse.fname,
            percentage = dataResponse.percentage,
            result = dataResponse.result,
            sname = dataResponse.sname
        )
    }
}