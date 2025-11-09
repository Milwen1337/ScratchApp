package com.milwen.restapi.data.service

import com.milwen.restapi.data.response.ActivationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ActivationService {

    @GET("version")
    suspend fun activate(
        @Query("code") code: String
    ): Response<ActivationResponse>

}