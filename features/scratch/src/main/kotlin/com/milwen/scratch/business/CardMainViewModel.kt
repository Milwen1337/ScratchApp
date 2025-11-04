package com.milwen.scratch.business

import androidx.lifecycle.viewModelScope
import com.milwen.baseline.business.BaseViewModel
import kotlinx.coroutines.launch

class CardMainViewModel(

): BaseViewModel<CardMainViewModel.State>(State()) {

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            state = state.copy(
                exampleValue = true
            )
        }
    }

    data class State(
        val exampleValue: Boolean = false,
    ) : BaseState
}