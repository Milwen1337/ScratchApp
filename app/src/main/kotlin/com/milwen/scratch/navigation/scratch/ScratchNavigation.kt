package com.milwen.scratch.navigation.scratch

import androidx.core.os.BundleCompat.getSerializable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.milwen.navigation.business.FeatureNavigation
import com.milwen.scratch.business.ScratchState
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
                onActivationClick = { state ->
                    navController.navigate(ScratchDestination.Activation.routeWithArg(state))
                },
                onScratchClick = { state ->
                    navController.navigate(ScratchDestination.Scratch.routeWithArg(state))
                },
            )
        }
        navGraphBuilder.composable(
            route = ScratchDestination.Scratch.route,
            arguments = listOf(
                navArgument(ScratchDestination.Scratch.ARG_SCRATCH_STATE) {
                    type = NavType.EnumType(ScratchState::class.java)
                    nullable = false
                }
            ),
        ) { backStackEntry ->
            val state = requireNotNull(
                getSerializable(
                    requireNotNull(backStackEntry.arguments),
                    ScratchDestination.Scratch.ARG_SCRATCH_STATE,
                    ScratchState::class.java
                )
            )

            ScratchScreen(
                scratchState = state,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        navGraphBuilder.composable(
            route = ScratchDestination.Activation.route,
            arguments = listOf(
                navArgument(ScratchDestination.Activation.ARG_SCRATCH_STATE) {
                    type = NavType.EnumType(ScratchState::class.java)
                    nullable = false
                }
            ),
        ) { backStackEntry ->
            val state = requireNotNull(
                getSerializable(
                    requireNotNull(backStackEntry.arguments),
                    ScratchDestination.Activation.ARG_SCRATCH_STATE,
                    ScratchState::class.java
                )
            )

            ActivationScreen(
                scratchState = state,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}