package com.sirelon.magicbuttons.designsystem.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sirelon.magicbuttons.designsystem.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MagicBlueButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {

    Box(
        // TODO: Size
        modifier = modifier
            .size(width = 150.dp, height = 52.dp)
            .drawWithCache {
                val strokeWidth = 1.dp.toPx()

                val mainColor = Color(0xB2CCFF)
                val bgGradient = Brush.verticalGradient(
                    colors = listOf(
                        mainColor.copy(alpha = 0.15f),
                        mainColor.copy(alpha = 0.20f)
                    )
                )

                val borderGradient = Brush.verticalGradient(
                    colors = listOf(
                        mainColor.copy(alpha = 0.1f),
                        mainColor.copy(alpha = 0.05f)
                    )
                )

                val cornerRadius = CornerRadius(16.dp.toPx())

                onDrawBehind {
                    drawRoundRect(
                        brush = bgGradient,
                        blendMode = BlendMode.Hardlight,
                        cornerRadius = cornerRadius,
                    )

                    drawRoundRect(
                        brush = borderGradient,
                        style = Stroke(
                            width = strokeWidth,
                        ),
                        cornerRadius = cornerRadius,
                    )
                }

            },
    ) {
        ButtonText(text)
    }
}

@Composable
private fun BoxScope.ButtonText(text: String) {
    Text(
        modifier = Modifier.align(Alignment.Center),
        text = text,
        style = TextStyle(
            fontWeight = FontWeight.W600,
            fontSize = 18.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
        )
    )
}

@Preview
@Composable
private fun MagicBlueButtonPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding(),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color(0xFF4A5B7C))
                    .blur(50.dp)
            )

            MagicBlueButton("Cancel", onClick = {})
        }
    }
}