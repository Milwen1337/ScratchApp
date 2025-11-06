package com.milwen.scratch.domain

interface ScratchUseCase {
    suspend fun scratch(): String
}