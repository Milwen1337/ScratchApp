package com.milwen.scratch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.milwen.baseline.presentation.ScratchTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScratchTheme {
                MainScreen(
                    onApplicationClose = ::onApplicationClose,
                )
            }
        }
    }

    private fun onApplicationClose() {
        finishAndRemoveTask()
    }
}