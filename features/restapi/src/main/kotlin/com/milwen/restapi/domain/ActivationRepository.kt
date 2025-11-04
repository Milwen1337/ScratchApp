package com.milwen.restapi.domain

interface ActivationRepository {
    suspend fun activate(code: String): Int?
}