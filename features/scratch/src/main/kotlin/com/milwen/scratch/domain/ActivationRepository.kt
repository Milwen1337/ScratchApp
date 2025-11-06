package com.milwen.scratch.domain

interface ActivationRepository {
    suspend fun activate(code: String)
}