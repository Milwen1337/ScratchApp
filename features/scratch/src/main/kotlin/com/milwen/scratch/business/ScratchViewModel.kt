package com.milwen.scratch.business

import androidx.lifecycle.viewModelScope
import com.milwen.baseline.business.BaseViewModel
import com.milwen.baseline.business.ScreenState
import com.milwen.scratch.data.ScratchCard
import com.milwen.scratch.data.ScratchState
import com.milwen.scratch.domain.ScratchCardObserveUseCase
import com.milwen.scratch.domain.ScratchUseCase
import kotlinx.coroutines.launch

class ScratchViewModel(
    private val scratchCardObserveUseCase: ScratchCardObserveUseCase,
    private val scratchUseCase: ScratchUseCase,
): BaseViewModel<ScratchViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            scratchCardObserveUseCase().collect { card ->
                state = state.copy(
                    screenState = null,
                    scratchCard = card,
                    canScratch = card.status is ScratchState.Unscratched,
                )
            }
        }
    }

    fun scratch() = viewModelScope.launch {
        if (!state.canScratch) return@launch

        state = state.copy(screenState = ScreenState.Loading("Scratching…"))
        runCatching { scratchUseCase.scratch() }
            .onSuccess { state = state.copy(screenState = null) }
            .onFailure { e -> state = state.copy(screenState = ScreenState.Error(e.message ?: "Scratch failed")) }
    }

    data class State(
        val screenState: ScreenState? = ScreenState.Loading("Loading"),
        val scratchCard: ScratchCard = ScratchCard(ScratchState.Unscratched),
        val canScratch: Boolean = false,
    ) : BaseState
}