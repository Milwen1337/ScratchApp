package com.milwen.baseline.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.milwen.baseline.business.ScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    state: ScreenState? = null,
    snackbar: BaseSnackbar? = null,
    onRetry: (() -> Unit)? = null,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackbar) {
        snackbar?.let {
            snackbarHostState.showSnackbar(
                message = snackbar.message,
                duration = when(snackbar.duration) {
                    BaseSnackbarDuration.Short -> SnackbarDuration.Short
                    BaseSnackbarDuration.Long -> SnackbarDuration.Long
                    BaseSnackbarDuration.Indefinite -> SnackbarDuration.Indefinite
                }
            )
        }
    }

    Scaffold(
        containerColor = ContentBackground,
        modifier = modifier.fillMaxSize(),
        topBar = {
            topBar?.invoke()
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                snackbar?.let {
                    val contentColor = when (it.type) {
                        SnackbarType.Default -> White
                    }

                    Snackbar(
                        modifier = Modifier
                            .padding(12.dp)
                            .wrapContentWidth()
                            .widthIn(max = 300.dp),
                        containerColor = LightBlue,
                        contentColor = contentColor,
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                style = MaterialTheme.typography.bodyMedium,
                                text = it.message,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            bottomBar?.invoke()
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when(state) {
                is ScreenState.Error -> ErrorScreen(state)
                is ScreenState.Loading -> LoadingScreen()
                is ScreenState.NoInternet -> NoInternetScreen(state)
                null -> content()
            }
        }
    }

}

@Composable
fun LoadingScreen() {
    Text(text = "Loading")
}


@Composable
fun ErrorScreen(state: ScreenState?) {
    if(state is ScreenState.Error) {
        Text(text = "There is some error: ${state.message}")
    }
}

@Composable
fun NoInternetScreen(state: ScreenState?) {
    if(state is ScreenState.NoInternet) {
        Text(text = "Check internet connection: ${state.message}")
    }
}