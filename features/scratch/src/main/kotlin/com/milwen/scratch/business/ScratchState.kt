package com.milwen.scratch.business

sealed class ScratchState {
    object Unscratched : ScratchState()
    data class Scratched(val revealCode: String) : ScratchState()
    object Activated : ScratchState()
}