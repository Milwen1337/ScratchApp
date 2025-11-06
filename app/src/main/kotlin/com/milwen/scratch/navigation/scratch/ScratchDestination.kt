package com.milwen.scratch.navigation.scratch

import com.milwen.navigation.business.NavDestination

interface ScratchDestination: NavDestination {

    data object CardMain : ScratchDestination {
        override val route = "cardMain"
    }

    data object Scratch : ScratchDestination {
        override val route = "cardMain/scratch/"
    }

    data object Activation : ScratchDestination {
        override val route = "cardMain/activation/"
    }

}