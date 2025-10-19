package com.sirelon.magicbuttons.designsystem.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sirelon.magicbuttons.designsystem.AppTheme

@Composable
fun MagicBlueButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {

}

@Composable
private fun MagicBlueButtonPreview() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            MagicBlueButton("Cancel", onClick = {})
        }
    }
}