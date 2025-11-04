package com.milwen.restapi.data.repository

import com.milwen.restapi.data.service.ActivationService
import com.milwen.restapi.domain.ActivationRepository
import com.milwen.scratch.restapi.BuildConfig
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ActivationRepositoryImpl(
    private val appScope: CoroutineScope
): ActivationRepository {

    private val activationService: ActivationService by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
        val okHttp = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val moshi = Moshi.Builder().build()
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ActivationService::class.java)
    }

    override suspend fun activate(code: String): Int? {
        val response = activationService.activate(code)

        return if (response.isSuccessful) {
            response.body()?.android
        } else {
            null
        }
    }
}