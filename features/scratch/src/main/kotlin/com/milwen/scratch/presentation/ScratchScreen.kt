package com.milwen.scratch.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.milwen.baseline.business.ScreenState
import com.milwen.baseline.presentation.BaseAppBar
import com.milwen.baseline.presentation.BaseScreen
import com.milwen.baseline.presentation.BaseSnackbar
import com.milwen.baseline.presentation.BaseSnackbarDuration
import com.milwen.baseline.presentation.BeyondBlue
import com.milwen.baseline.presentation.Green
import com.milwen.baseline.presentation.PrimaryButton
import com.milwen.baseline.presentation.SnackbarType
import com.milwen.baseline.presentation.White
import com.milwen.scratch.business.ScratchViewModel
import com.milwen.scratch.data.ScratchState
import org.koin.androidx.compose.koinViewModel

@Immutable
private interface ScratchScreenListener {
    fun onScratch()
}

@Composable
fun ScratchScreen(
    onBackClick: () -> Unit,
    viewModel: ScratchViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    val listener = object : ScratchScreenListener {
        override fun onScratch() {
            viewModel.scratch()
        }
    }

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

    BaseScreen(
        state = uiState.value.screenState,
        snackbar = snackBar.value,
        topBar = {
            BaseAppBar(
                title = "Card Scratch",
                backButtonAction = onBackClick,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                PrimaryButton(
                    isEnabled = uiState.value.canScratch,
                    text = "Scratch",
                    onClick = {
                        listener.onScratch()
                    }
                )

                val scratchState = uiState.value.scratchCard.status
                if (scratchState is ScratchState.Scratched) {
                    ScratchCode(
                        code = scratchState.revealCode,
                    )
                }
            }
        }
    )

}

@Composable
private fun ScratchCode(
    code: String
) {
    val outlineColor = BeyondBlue
    val backgroundColor = White

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .border(width = 2.dp, color = outlineColor, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 12.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = code,
            color = Green,
            style = MaterialTheme.typography.titleMedium
        )
    }
}