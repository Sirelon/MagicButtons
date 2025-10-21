package com.sirelon.magicbuttons.feature.blue

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.sirelon.magicbuttons.designsystem.buttons.ButtonText
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

        val bgColor by animateColorAsState(
            targetValue = if (pressed) Color(0xD10D1A35) else Color(0xFF4A5B7C),
            animationSpec = tween(durationMillis = 150, easing = EaseOut)
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


            if (false) {
                val radiusDp = 16.dp

                Box(
                    modifier = Modifier
                        .size(width = 150.dp, height = 60.dp)
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            drawRoundRect(
                                color = Color.Black.copy(alpha = 0.3f),
                                topLeft = Offset(-strokeWidth / 2, -strokeWidth / 2),
                                size = Size(size.width + strokeWidth, size.height + strokeWidth),
                                cornerRadius = CornerRadius(radiusDp.toPx()),
                                style = Stroke(width = strokeWidth)
                            )
                        }
                        .innerShadow(RoundedCornerShape(radiusDp)) {
                            this.color = Color(0xFF0D1626)
                            this.alpha = 0.25f
                            this.offset = Offset(x = 0f, y = 2.dp.toPx())
                            this.radius = 1.dp.toPx()
                        }
                ) {
                    Canvas(
                        modifier = Modifier
                            .matchParentSize()
                            .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                            .dropShadow(RoundedCornerShape(radiusDp)) {
                                color = Color(0xFF0D0F1A)
                            },
                    ) {
                        val strokeWidth = 1.dp.toPx()
                        val mainColor = Color(0xB2CCFF)
                        val bgGradient = Brush.verticalGradient(
                            listOf(
                                mainColor.copy(alpha = 0.15f),
                                mainColor.copy(alpha = 0.2f),
                            )
                        )

                        val borderGradient = Brush.verticalGradient(
                            listOf(
                                Color.White.copy(alpha = 0.1f),
                                Color.White.copy(alpha = 0.05f),
                            )
                        )

                        // ??
                        drawRoundRect(
                            color = Color.Transparent,
                            cornerRadius = CornerRadius(radiusDp.toPx()),
                            blendMode = BlendMode.Clear
                        )

                        drawRoundRect(
                            brush = bgGradient,
                            cornerRadius = CornerRadius(radiusDp.toPx()),
                            blendMode = BlendMode.Hardlight,
                        )
                        // Border?
//                drawRoundRect(
//                    brush = borderGradient,
//                    cornerRadius = CornerRadius(radiusDp.toPx()),
//                    style = Stroke(strokeWidth),
//                    // ??
//                    blendMode = BlendMode.Hardlight,
//                )
                    }

                    ButtonText("Cancel")
                }

            } else {
                MagicBlueButton("Cancel", onClick = {}, interaction = interaction)
            }
        }
    }
}

@Preview
@Composable
private fun BlueScreenUIPreview() {
    BlueScreenUI(onBack = {})
}