package com.milwen.baseline.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAppBar(
    title: String = "",
    backButtonAction: (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(text = title, style = MaterialTheme.typography.titleSmall)
        },
        modifier = Modifier
            .fillMaxWidth(),
        navigationIcon = {
            if(backButtonAction != null) {
                IconButton(onClick = { backButtonAction() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}