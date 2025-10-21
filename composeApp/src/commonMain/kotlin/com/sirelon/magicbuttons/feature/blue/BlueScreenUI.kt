package com.sirelon.magicbuttons.feature.blue

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.sirelon.magicbuttons.designsystem.buttons.MagicBlueButton
import magicbuttons.composeapp.generated.resources.Res
import magicbuttons.composeapp.generated.resources._6
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BlueScreenUI(onBack: () -> Unit) {
    BlueScreen(onBack = onBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BlueScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Blue Button Screen") },
                navigationIcon = {
                    MagicBlueButton(
                        text = "Back",
                        onClick = onBack,
                    )
                },
            )
        }
    ) { paddingValues ->

        val interaction = remember { MutableInteractionSource() }

        val pressed by interaction.collectIsPressedAsState()

        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentAlignment = Alignment.Center,
        ) {

            Image(
                modifier = Modifier
                    .matchParentSize()
                    .blur(50.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(Res.drawable._6),
                contentDescription = null,
            )

            val color by animateColorAsState(
                targetValue = if (pressed) Color(0xD10D1A35) else Color(0xFF4A5B7C)
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(color = color)
            )

            MagicBlueButton("Cancel", onClick = {}, interaction = interaction)
        }
    }
}

@Preview
@Composable
private fun BlueScreenUIPreview() {
    BlueScreenUI(onBack = {})
}