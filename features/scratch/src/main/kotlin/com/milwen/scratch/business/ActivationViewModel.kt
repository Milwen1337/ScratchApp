package com.milwen.scratch.business

import androidx.lifecycle.viewModelScope
import com.milwen.baseline.business.BaseViewModel
import com.milwen.baseline.business.ScreenState
import com.milwen.restapi.domain.ActivationRepository
import kotlinx.coroutines.launch
import java.util.UUID

class ActivationViewModel(
    val activationRepository: ActivationRepository
): BaseViewModel<ActivationViewModel.State>(State()) {

    fun generateCode() {
        viewModelScope.launch {
            state = state.copy(
                revealedCode = UUID.randomUUID().toString()
            )
        }
    }

    fun activateCard() {
        val code = state.revealedCode ?: return

        viewModelScope.launch {
            state = state.copy(
                screenState = ScreenState.Loading("Loading")
            )
            val newCode = activationRepository.activate(code)

            if (newCode == null) {
                // show error
            } else {
                state = state.copy(
                    scratchState = newCode.toCardState()
                )
            }
        }
    }

    fun Int.toCardState(): ScratchState? = if (this > SCRATCH_CODE_VALID) ScratchState.ACTIVATED else state.scratchState

    data class State(
        val screenState: ScreenState = ScreenState.Loading("Loading"),
        val scratchState: ScratchState? = null,
        val revealedCode: String? = null,
    ) : BaseState

    companion object {
        private const val SCRATCH_CODE_VALID = 277028
    }
}