package com.milwen.usecase.scratch

import com.milwen.database.data.dao.ScratchCardDao
import com.milwen.scratch.data.ScratchState
import com.milwen.scratch.data.ScratchCard
import com.milwen.scratch.domain.ScratchCardObserveUseCase
import com.milwen.usecase.map.FeatureScratchMapper.toScratchCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ScratchCardObserveUseCaseImpl(
    private val dao: ScratchCardDao
) : ScratchCardObserveUseCase {

    override fun invoke(): Flow<ScratchCard> =
        dao.observe()
            .map { it?.toScratchCard() ?: ScratchCard(ScratchState.Unscratched) }
}