package com.milwen.baseline.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        enabled = isEnabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = BeyondBlue,
            contentColor = White,
            disabledContainerColor = BeyondBlue.copy(alpha = 0.5f),
            disabledContentColor = White.copy(alpha = 0.5f),
        )
    ) {
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
