package com.sirelon.magicbuttons.designsystem

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

@ReadOnlyComposable
@Composable
fun appColors(): ColorScheme = MaterialTheme.colorScheme.copy(
    background = Color(0xFFE3E3E3)
)
