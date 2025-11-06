package com.milwen.baseline.presentation

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState

data class BaseSnackbar(
    val message: String,
    val type: SnackbarType,
    val duration: BaseSnackbarDuration = BaseSnackbarDuration.Short
)

enum class BaseSnackbarDuration {
    Short,
    Long,
    Indefinite
}

sealed class SnackbarType {
    object Default : SnackbarType()
}

suspend fun SnackbarHostState.show(base: BaseSnackbar) {
    val duration = when (base.duration) {
        BaseSnackbarDuration.Short -> SnackbarDuration.Short
        BaseSnackbarDuration.Long -> SnackbarDuration.Long
        BaseSnackbarDuration.Indefinite -> SnackbarDuration.Indefinite
    }

    showSnackbar(
        message = base.message,
        withDismissAction = true,
        duration = duration
    )
}