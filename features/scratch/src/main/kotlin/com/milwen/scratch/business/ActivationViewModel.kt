package com.milwen.scratch.business

import androidx.lifecycle.viewModelScope
import com.milwen.baseline.business.BaseViewModel
import com.milwen.baseline.business.ScreenState
import com.milwen.scratch.data.ScratchCard
import com.milwen.scratch.domain.ActivationRepository
import kotlinx.coroutines.launch

class ActivationViewModel(
    val activationRepository: ActivationRepository
): BaseViewModel<ActivationViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            activationRepository.observeScratchCard().collect { card ->
                state = state.copy(
                    scratchCard = card,
                    canActivate = card.status is ScratchState.Scratched
                )
            }
        }
    }

    fun activateCard() = viewModelScope.launch {
        val code = (state.scratchCard.status as? ScratchState.Scratched)?.revealCode
        if (code.isNullOrBlank()) {
            state = state.copy(screenState = ScreenState.Error("Please scratch the card first."))
            return@launch
        }

        state = state.copy(screenState = ScreenState.Loading("Activating…"))

        runCatching { activationRepository.activate(code) }
            .onSuccess {
                state = state.copy(screenState = null)
            }
            .onFailure { e ->
                state = state.copy(screenState = ScreenState.Error(e.message ?: "Activation failed"))
            }
    }

    data class State(
        val screenState: ScreenState? = ScreenState.Loading("Loading"),
        val scratchCard: ScratchCard = ScratchCard(ScratchState.Unscratched),
        val canActivate: Boolean = false,
    ) : BaseState
}