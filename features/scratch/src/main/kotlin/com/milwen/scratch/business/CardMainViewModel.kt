package com.milwen.scratch.business

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.milwen.baseline.business.BaseViewModel
import com.milwen.baseline.business.ScreenState
import com.milwen.scratch.data.ScratchCard
import com.milwen.scratch.data.ScratchState
import com.milwen.scratch.domain.ScratchCardObserveUseCase
import kotlinx.coroutines.launch

class CardMainViewModel(
    private val scratchCardObserveUseCase: ScratchCardObserveUseCase,
): BaseViewModel<CardMainViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            scratchCardObserveUseCase().collect { card ->
                Log.d("CardMainViewModel", "card: $card")
                state = state.copy(
                    screenState = null,
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