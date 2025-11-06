package com.milwen.usecase.scratch

import com.milwen.database.data.dao.ScratchCardDao
import com.milwen.database.data.entity.ScratchCardEntity
import com.milwen.database.data.enum.ScratchCardEntityStatus
import com.milwen.scratch.domain.ScratchUseCase
import kotlinx.coroutines.delay
import java.util.UUID

class ScratchUseCaseImpl(
    private val scratchCardDao: ScratchCardDao
) : ScratchUseCase {

    override suspend fun scratch(): String {
        delay(SCRATCH_DELAY) // artificial delay
        val code = UUID.randomUUID().toString()

        scratchCardDao.upsert(
            ScratchCardEntity(
                id = ScratchCardEntity.SINGLETON_ID,
                status = ScratchCardEntityStatus.SCRATCHED,
                revealCode = code
            )
        )
        return code
    }

    companion object {
        private const val SCRATCH_DELAY = 2_000L
    }
}