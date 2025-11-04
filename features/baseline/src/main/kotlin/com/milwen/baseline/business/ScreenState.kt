package com.milwen.baseline.business

sealed class ScreenState() {
    data class Error(val message: String) : ScreenState()
    data class NoInternet(val message: String) : ScreenState()
    data class Loading(val message: String) : ScreenState()
}