package com.milwen.restapi.di

import com.milwen.restapi.data.Config
import com.milwen.restapi.data.service.ActivationService
import com.milwen.scratch.restapi.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val restModule = module {
    single { Moshi.Builder().build() }
    single {
        HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(Config.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Config.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Config.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }
    single<ActivationService> {
        get<Retrofit>().create(ActivationService::class.java)
    }
}