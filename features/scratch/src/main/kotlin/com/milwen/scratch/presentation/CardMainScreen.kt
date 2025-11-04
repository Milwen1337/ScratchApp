package com.milwen.scratch.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.milwen.baseline.business.ScreenState
import com.milwen.baseline.presentation.BaseAppBar
import com.milwen.baseline.presentation.BaseScreen
import com.milwen.baseline.presentation.BaseSnackbar
import com.milwen.baseline.presentation.BaseSnackbarDuration
import com.milwen.baseline.presentation.SnackbarType
import com.milwen.scratch.business.CardMainViewModel
import com.milwen.scratch.business.ScratchState
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardMainScreen(
    onActivationClick: (ScratchState) -> Unit,
    onScratchClick: (ScratchState) -> Unit,
    viewModel: CardMainViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    val snackbarHostState = remember { SnackbarHostState() }

    /*
    LaunchedEffect(Unit) {
        snackbarHostState.showSnackbar(
            BaseSnackbar(
                message = "",
                type = SnackbarType.Default,
                duration = BaseSnackbarDuration.Short
            )
        )
    }*/

    BaseScreen(
        state = ScreenState.Loading("Loading Card Content"),
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                BaseAppBar(
                    title = "Card",
                )
            }
        },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        }
    )

}