package com.milwen.scratch.domain

import com.milwen.scratch.data.ScratchCard
import kotlinx.coroutines.flow.Flow

interface ScratchCardObserveUseCase {
    operator fun invoke(): Flow<ScratchCard>
}