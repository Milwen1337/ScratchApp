package com.milwen.scratch.business

import androidx.lifecycle.viewModelScope
import com.milwen.baseline.business.BaseViewModel
import kotlinx.coroutines.launch
import java.util.UUID

class ScratchViewModel(

): BaseViewModel<ScratchViewModel.State>(State()) {

    fun generateCode() {
        viewModelScope.launch {
            state = state.copy(
                revealedCode = UUID.randomUUID().toString()
            )
        }
    }

    data class State(
        val revealedCode: String? = null,
    ) : BaseState
}