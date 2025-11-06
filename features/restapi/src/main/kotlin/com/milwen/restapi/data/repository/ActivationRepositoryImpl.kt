package com.milwen.restapi.data.repository

import com.milwen.database.data.dao.ScratchCardDao
import com.milwen.database.data.entity.ScratchCardEntity
import com.milwen.database.data.enum.ScratchCardEntityStatus
import com.milwen.restapi.data.service.ActivationService
import com.milwen.scratch.domain.ActivationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ActivationRepositoryImpl(
    private val appScope: CoroutineScope,
    private val activationService: ActivationService,
    private val scratchCardDao: ScratchCardDao,
): ActivationRepository {

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