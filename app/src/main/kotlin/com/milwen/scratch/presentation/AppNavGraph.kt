package com.milwen.scratch.presentation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.milwen.navigation.business.FeatureNavigation
import com.milwen.scratch.navigation.scratch.ScratchDestination
import com.milwen.scratch.navigation.scratch.ScratchNavigation

@Composable
fun AppNavGraph(
    navController: NavHostController,
    onApplicationClose: () -> Unit,
) {

    val featuresNavigation: List<FeatureNavigation> = listOf(
        ScratchNavigation(onApplicationClose),
    )

    NavHost(
        navController = navController,
        startDestination = ScratchDestination.CardMain.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        featuresNavigation.forEach { it.register(this, navController) }
    }
}