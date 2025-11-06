package com.milwen.scratch.navigation.scratch

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.milwen.navigation.business.FeatureNavigation
import com.milwen.scratch.presentation.ActivationScreen
import com.milwen.scratch.presentation.CardMainScreen
import com.milwen.scratch.presentation.ScratchScreen

class ScratchNavigation(
    val onApplicationClose: () -> Unit
): FeatureNavigation {
    override fun register(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable(
            route = ScratchDestination.CardMain.route
        ) {
            CardMainScreen(
                onActivationClick = {
                    navController.navigate(ScratchDestination.Activation.route)
                },
                onScratchClick = {
                    navController.navigate(ScratchDestination.Scratch.route)
                },
            )
        }
        navGraphBuilder.composable(
            route = ScratchDestination.Scratch.route,
        ) {
            ScratchScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        navGraphBuilder.composable(
            route = ScratchDestination.Activation.route,
        ) {
            ActivationScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}