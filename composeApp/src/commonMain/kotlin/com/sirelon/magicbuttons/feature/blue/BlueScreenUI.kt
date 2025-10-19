package com.sirelon.magicbuttons.feature.blue

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sirelon.magicbuttons.designsystem.buttons.MagicBlueButton

@Composable
fun BlueScreenUI() {


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BlueScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Blue Button Screen") },
                // TODO:
                navigationIcon = {},
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentAlignment = Alignment.Center,
        ) {
            MagicBlueButton("Cancel", onClick = {})
        }
    }
}