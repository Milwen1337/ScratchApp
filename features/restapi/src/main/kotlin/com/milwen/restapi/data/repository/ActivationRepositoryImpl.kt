package com.milwen.restapi.data.repository

import com.milwen.database.data.dao.ScratchCardDao
import com.milwen.database.data.entity.ScratchCardEntity
import com.milwen.database.data.enum.ScratchCardEntityStatus
import com.milwen.restapi.data.map.FeatureScratchMapper.toScratchCard
import com.milwen.restapi.data.service.ActivationService
import com.milwen.scratch.business.ScratchState
import com.milwen.scratch.data.ScratchCard
import com.milwen.scratch.domain.ActivationRepository
import com.milwen.scratch.restapi.BuildConfig
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ActivationRepositoryImpl(
    private val appScope: CoroutineScope,
    private val scratchCardDao: ScratchCardDao,
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

    override fun observeScratchCard(): Flow<ScratchCard> =
        scratchCardDao.observe()
            .map { it?.toScratchCard() ?: ScratchCard(ScratchState.Unscratched) }

    override suspend fun activate(code: String) {
        appScope.launch {
            val resp = activationService.activate(code)

            if (!resp.isSuccessful) throw HttpException(resp)

            val validationCode = resp.body()?.android
                ?: error("Missing validation code")

            val entity = if (validationCode.isCodeValid()) {
                ScratchCardEntity(
                    id = ScratchCardEntity.SINGLETON_ID,
                    status = ScratchCardEntityStatus.ACTIVATED,
                    revealCode = null,
                )
            } else {
                ScratchCardEntity(
                    id = ScratchCardEntity.SINGLETON_ID,
                    status = ScratchCardEntityStatus.SCRATCHED,
                    revealCode = code,
                )
            }

            scratchCardDao.upsert(entity)
        }
    }

    private fun Int.isCodeValid() = this > SCRATCH_CODE_VALID

    companion object {
        private const val SCRATCH_CODE_VALID = 277028
    }
}