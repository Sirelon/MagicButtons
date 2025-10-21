package com.sirelon.magicbuttons.feature.blue

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.sirelon.magicbuttons.designsystem.buttons.MagicBlueButton
import magicbuttons.composeapp.generated.resources.Res
import magicbuttons.composeapp.generated.resources._6
import magicbuttons.composeapp.generated.resources.ic_arrow_left
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.random.Random

private const val ANIMATION_DURATION = 150
private val ANIMATION_EASING = EaseOut

@Composable
fun BlueScreen(counter: Int, onBack: () -> Unit) {
    BlueScreenUI(counter = counter, onBack = onBack)
}

@Composable
private fun BlueScreenUI(counter: Int, onBack: () -> Unit) {
    Scaffold(
        contentWindowInsets = WindowInsets(),
        topBar = { TopBar(counter, onBack) },
    ) { paddingValues ->
        val interaction = remember { MutableInteractionSource() }

        val pressed by interaction.collectIsPressedAsState()

        val bgColor by animateColorAsState(
            targetValue = if (pressed) Color(0xD10D1A35) else Color(0xFF4A5B7C),
            animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = ANIMATION_EASING)
        )

        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier
                    .matchParentSize()
                    .drawWithContent {
                        drawContent()
                        drawRect(color = bgColor)
                    }
                    .blur(50.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(Res.drawable._6),
                contentDescription = null,
            )

            MagicBlueButton(
                modifier = Modifier,
                text = "Cancel",
                onClick = {},
                interaction = interaction,
                animationEasing = ANIMATION_EASING,
                animationDuration = ANIMATION_DURATION,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(counter: Int, onBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Blue Button Screen (counter: $counter)",
                maxLines = 1,
                autoSize = TextAutoSize.StepBased(),
            )
        },
        navigationIcon = {
            IconButton(onBack) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_left),
                    contentDescription = null,
                )
            }
        },
    )
}

@Preview
@Composable
private fun BlueScreenUIPreview() {
    BlueScreen(
        counter = Random.nextInt(0, 999),
        onBack = {},
    )
}