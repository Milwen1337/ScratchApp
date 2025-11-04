package com.milwen.scratch.presentation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.milwen.navigation.business.FeatureNavigation

@Composable
fun AppNavGraph(
    navController: NavHostController,
    onApplicationClose: () -> Unit,
) {

    val featuresNavigation: List<FeatureNavigation> = listOf(
        MainNavigation(),
        ChatNavigation(onNavigationClick),
        DashboardNavigation(onNavigationClick),
        SignNavigation(onApplicationClose)
    )

    NavHost(
        navController = navController,
        startDestination = MainDestination.SplashScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        featuresNavigation.forEach { it.register(this, navController) }
    }
}