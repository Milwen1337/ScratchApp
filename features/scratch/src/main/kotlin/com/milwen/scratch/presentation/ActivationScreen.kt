package com.milwen.scratch.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.milwen.baseline.business.ScreenState
import com.milwen.baseline.presentation.BaseAppBar
import com.milwen.baseline.presentation.BaseScreen
import com.milwen.baseline.presentation.BaseSnackbar
import com.milwen.baseline.presentation.BaseSnackbarDuration
import com.milwen.baseline.presentation.PrimaryButton
import com.milwen.baseline.presentation.SnackbarType
import com.milwen.scratch.business.ActivationViewModel
import org.koin.androidx.compose.koinViewModel

@Immutable
private interface ActivationScreenListener {
    fun onActivate()
}

@Composable
fun ActivationScreen(
    onBackClick: () -> Unit,
    viewModel: ActivationViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val snackBar = remember { mutableStateOf<BaseSnackbar?>(null) }

    LaunchedEffect(uiState.value.screenState) {
        val state = uiState.value.screenState
        if (state is ScreenState.Error) {
            snackBar.value = BaseSnackbar(
                message = state.message,
                type = SnackbarType.Default,
                duration = BaseSnackbarDuration.Short
            )
        } else {
            snackBar.value = null
        }
    }

    val listener = object : ActivationScreenListener {
        override fun onActivate() {
            viewModel.activateCard()
        }
    }

    BaseScreen(
        state = uiState.value.screenState,
        snackbar = snackBar.value,
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
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                PrimaryButton(
                    isEnabled = uiState.value.canActivate,
                    text = "Activate",
                    onClick = {
                        listener.onActivate()
                    }
                )
            }
        }
    )

}