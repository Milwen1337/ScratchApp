package com.milwen.usecase.scratch

import com.milwen.database.data.dao.ScratchCardDao
import com.milwen.database.data.entity.ScratchCardEntity
import com.milwen.database.data.enum.ScratchCardEntityStatus
import com.milwen.scratch.data.ScratchState
import com.milwen.scratch.data.ScratchCard
import com.milwen.scratch.domain.ScratchCardObserveUseCase
import com.milwen.usecase.map.FeatureScratchMapper.toScratchCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class ScratchCardObserveUseCaseImpl(
    private val dao: ScratchCardDao
) : ScratchCardObserveUseCase {

    override fun invoke(): Flow<ScratchCard> =
        dao.observe()
            .onStart {
                // Create Unscratched one if empty
                if (dao.get() == null) {
                    dao.upsert(
                        ScratchCardEntity(
                            id = ScratchCardEntity.SINGLETON_ID,
                            status = ScratchCardEntityStatus.UNSCRATCHED,
                            revealCode = null
                        )
                    )
                }
            }
            .map { it?.toScratchCard() ?: ScratchCard(ScratchState.Unscratched) }
            .distinctUntilChanged()
}