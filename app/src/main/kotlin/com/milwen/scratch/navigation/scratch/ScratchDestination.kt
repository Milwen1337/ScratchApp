package com.milwen.scratch.navigation.scratch

import com.milwen.navigation.business.NavDestination
import com.milwen.scratch.business.ScratchState

interface ScratchDestination: NavDestination {

    data object CardMain : ScratchDestination {
        override val route = "cardMain"
    }

    data object Scratch : ScratchDestination {
        const val ARG_SCRATCH_STATE = "scratchState"
        override val route = "cardMain/scratch/{$ARG_SCRATCH_STATE}"

        fun routeWithArg(scratchState: ScratchState) = "cardMain/scratch/$scratchState"
    }

    data object Activation : ScratchDestination {
        const val ARG_SCRATCH_STATE = "scratchState"
        override val route = "cardMain/activation/{${ARG_SCRATCH_STATE}}"

        fun routeWithArg(scratchState: ScratchState) = "cardMain/activation/$scratchState"
    }

}