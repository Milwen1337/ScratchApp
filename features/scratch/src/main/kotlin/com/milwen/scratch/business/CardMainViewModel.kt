package com.milwen.scratch.business

import androidx.lifecycle.viewModelScope
import com.milwen.baseline.business.BaseViewModel
import com.milwen.baseline.business.ScreenState
import com.milwen.scratch.data.ScratchCard
import com.milwen.scratch.domain.ScratchCardObserveUseCase
import kotlinx.coroutines.launch

class CardMainViewModel(
    private val scratchCardObserveUseCase: ScratchCardObserveUseCase,
): BaseViewModel<CardMainViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            scratchCardObserveUseCase().collect { card ->
                state = state.copy(
                    scratchCard = card,
                    canScratch = card.status is ScratchState.Unscratched,
                    canActivate = card.status is ScratchState.Scratched
                )
            }
        }
    }

    data class State(
        val screenState: ScreenState? = ScreenState.Loading("Loading"),
        val scratchCard: ScratchCard = ScratchCard(ScratchState.Unscratched),
        val canScratch: Boolean = false,
        val canActivate: Boolean = false,
    ) : BaseState
}