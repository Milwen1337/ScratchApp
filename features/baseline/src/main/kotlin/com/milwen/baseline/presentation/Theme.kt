package com.milwen.baseline.presentation

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = BeyondBlue,
    onPrimary = White,
    secondary = DarkBlue,
    onSecondary = White,
    background = ContentBackground,
    onBackground = Black,
    surface = ContentBackground,
    onSurface = Black,
    error = Error,
)

@Composable
fun ScratchTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        else -> LightColorScheme
    }

    MaterialTheme(
        typography = AppTypography,
        colorScheme = colorScheme,
        content = content
    )
}