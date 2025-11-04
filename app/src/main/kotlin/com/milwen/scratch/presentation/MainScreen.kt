package com.milwen.scratch.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.milwen.scratch.business.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = koinViewModel(),
    onApplicationClose: () -> Unit,
) {
    AppNavGraph(
        navController = rememberNavController(),
        onApplicationClose = onApplicationClose,
    )
}