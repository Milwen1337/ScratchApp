package com.milwen.baseline.business

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<T : BaseViewModel.BaseState>(value: T) : ViewModel() {

    private val _uiState = MutableStateFlow(value)
    val uiState: StateFlow<T> = _uiState

    protected var state: T
        get() = _uiState.value
        set(value) { _uiState.value = value }

    interface BaseState
}