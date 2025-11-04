package com.milwen.baseline.presentation

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