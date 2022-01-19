package com.pss.data.remote.api

import com.pss.data.remote.model.LovePercentageResponse
import com.pss.data.remote.model.TestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LoveCalculatorApi {
    @GET("/getPercentage")
    suspend fun getPercentage(
        @Header("x-rapidapi-key") key: String,
        @Header("x-rapidapi-host") host: String,
        @Query("sname") sName : String,
        @Query("fname") fName : String
        ) : Response<LovePercentageResponse>
}