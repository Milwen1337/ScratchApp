package com.milwen.scratch.data

sealed class ScratchState {
    object Unscratched : ScratchState()
    data class Scratched(val revealCode: String) : ScratchState()
    object Activated : ScratchState()
}