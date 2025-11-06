package com.milwen.scratch.business

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.milwen.baseline.business.BaseViewModel
import com.milwen.baseline.business.ScreenState
import com.milwen.scratch.data.ScratchCard
import com.milwen.scratch.data.ScratchState
import com.milwen.scratch.domain.ActivationRepository
import com.milwen.scratch.domain.ScratchCardObserveUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ActivationViewModel(
    private val appScope: CoroutineScope,
    private val activationRepository: ActivationRepository,
    private val scratchCardObserveUseCase: ScratchCardObserveUseCase,
): BaseViewModel<ActivationViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            scratchCardObserveUseCase().collect { card ->
                Log.d("ActivationViewModel", "card: $card")
                state = state.copy(
                    scratchCard = card,
                    canActivate = card.status is ScratchState.Scratched
                )
            }
        }
    }

    fun activateCard() {
        val code = (state.scratchCard.status as? ScratchState.Scratched)?.revealCode
        if (code.isNullOrBlank()) {
            viewModelScope.launch {
                state = state.copy(screenState = ScreenState.Error("Please scratch the card first."))
            }
            return
        }

        viewModelScope.launch {
            state = state.copy(screenState = ScreenState.Loading("Activating…"))
        }

        val job = appScope.launch {
            runCatching { activationRepository.activate(code) }
                .onFailure {}
        }

        viewModelScope.launch {
            job.invokeOnCompletion { cause ->
                state = if (cause == null) {
                    state.copy(screenState = null)
                } else {
                    state.copy(screenState = ScreenState.Error(cause.message ?: "Activation failed"))
                }
            }
        }
    }

    data class State(
        val screenState: ScreenState? = null,
        val scratchCard: ScratchCard = ScratchCard(ScratchState.Unscratched),
        val canActivate: Boolean = false,
    ) : BaseState
}