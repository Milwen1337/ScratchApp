package com.milwen.scratch.domain

import com.milwen.scratch.data.ScratchCard
import kotlinx.coroutines.flow.Flow

interface ActivationRepository {
    fun observeScratchCard(): Flow<ScratchCard>
    suspend fun activate(code: String)
}