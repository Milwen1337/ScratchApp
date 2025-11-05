package com.milwen.scratch.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.milwen.baseline.business.ScreenState
import com.milwen.baseline.presentation.BaseAppBar
import com.milwen.baseline.presentation.BaseScreen
import com.milwen.baseline.presentation.PrimaryButton
import com.milwen.scratch.business.ActivationViewModel
import com.milwen.scratch.business.ScratchState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ActivationScreen(
    scratchState: ScratchState,
    onBackClick: () -> Unit,
    viewModel: ActivationViewModel = koinViewModel()
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
        state = ScreenState.Loading("Loading..."),
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                BaseAppBar(
                    title = "Card Activation",
                    backButtonAction = onBackClick,
                )
            }
        },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                PrimaryButton(
                    text = "Activate",
                    onClick = {
                        
                    }
                )
            }
        }
    )

}