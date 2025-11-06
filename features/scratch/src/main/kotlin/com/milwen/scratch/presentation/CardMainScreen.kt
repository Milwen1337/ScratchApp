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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.milwen.baseline.presentation.BaseAppBar
import com.milwen.baseline.presentation.BaseScreen
import com.milwen.baseline.presentation.BeyondBlue
import com.milwen.baseline.presentation.Black80
import com.milwen.baseline.presentation.Green
import com.milwen.baseline.presentation.Orange
import com.milwen.baseline.presentation.PrimaryButton
import com.milwen.baseline.presentation.White
import com.milwen.scratch.business.CardMainViewModel
import com.milwen.scratch.data.ScratchState
import org.koin.androidx.compose.koinViewModel

@Immutable
private interface CardMainScreenListener {
    fun onScratch()
    fun onActivate()
}

@Composable
fun CardMainScreen(
    onActivationClick: () -> Unit,
    onScratchClick: () -> Unit,
    viewModel: CardMainViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    val listener = object : CardMainScreenListener {
        override fun onScratch() {
            onActivationClick.invoke()
        }
        override fun onActivate() {
            onScratchClick.invoke()
        }
    }

    BaseScreen(
        state = uiState.value.screenState,
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
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ScratchCardStateView(
                    scratchState = uiState.value.scratchCard.status,
                )

                PrimaryButton(
                    isEnabled = uiState.value.canScratch,
                    text = "Scratch",
                    onClick = {
                        listener.onScratch()
                    }
                )

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

@Composable
private fun ScratchCardStateView(
    scratchState: ScratchState
) {
    val outlineColor = BeyondBlue
    val backgroundColor = White

    val textColor = when (scratchState) {
        ScratchState.Activated -> Green
        is ScratchState.Scratched -> Orange
        ScratchState.Unscratched -> Black80
    }

    val label = when (scratchState) {
        ScratchState.Activated -> "Activated"
        is ScratchState.Scratched -> "Scratched"
        ScratchState.Unscratched -> "Unscratched"
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .border(width = 2.dp, color = outlineColor, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 12.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = textColor,
            style = MaterialTheme.typography.titleMedium
        )
    }
}