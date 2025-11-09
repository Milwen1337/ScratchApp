package com.milwen.navigation.business

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureNavigation {
    fun register(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    )
}