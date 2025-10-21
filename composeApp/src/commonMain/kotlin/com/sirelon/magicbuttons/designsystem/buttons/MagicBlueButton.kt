package com.sirelon.magicbuttons.designsystem.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sirelon.magicbuttons.designsystem.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MagicBlueButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    interaction: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val radiusDp = 16.dp

    val pressed by interaction.collectIsPressedAsState()

    Box(
        // TODO: Size
        modifier = modifier
            .size(width = 150.dp, height = 60.dp)
            .clickable(
                enabled = true,
                onClick = onClick,
                indication = null,
                interactionSource = interaction,
            )
            .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
            .padding(8.dp)
            .dropShadow(shape = RoundedCornerShape(radiusDp)) {
                this.radius = 4.dp.toPx()
                this.offset = Offset(x = 0f, y = 4.dp.toPx())
                this.color = Color(0xFF000000)
                this.alpha = 0.25f
//                this.color = Color.Red
            }
            .dropShadow(shape = RoundedCornerShape(radiusDp)) {
                this.spread = -2.dp.toPx()
                this.radius = 4.dp.toPx()
                this.offset = Offset(x = 0f, y = 4.dp.toPx())
//                this.color = Color.Green

                this.color = Color(0xFF000000)
                this.alpha = 0.3f
            }
            .drawBehind {
                drawRoundRect(
                    color = Color.Transparent,
                    cornerRadius = CornerRadius(radiusDp.toPx()),
                    blendMode = BlendMode.Clear
                )
            }
            .blueBg(radiusDp)
    ) {
        ButtonText(text)
    }
}

private fun Modifier.blueBg(radiusDp: Dp): Modifier = this.drawWithCache {
    val strokeWidth = 1.dp.toPx()

    val mainColor = Color(0xB2CCFF)
    val bgGradient = Brush.verticalGradient(
        colors = listOf(
            mainColor.copy(alpha = 0.3f),
            mainColor.copy(alpha = 0.10f)
        )
    )

    val borderGradient = Brush.verticalGradient(
        colors = listOf(
            mainColor.copy(alpha = 0.1f),
            mainColor.copy(alpha = 0.05f)
        )
    )

    val cornerRadius = CornerRadius(radiusDp.toPx())

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
}

@Composable
internal fun BoxScope.ButtonText(text: String) {
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


            Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                MagicBlueButton("Cancel", onClick = {})
                val radiusDp = 16.dp
                Box(
                    Modifier
                        .size(120.dp)
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .dropShadow(
                            shape = RoundedCornerShape(12.dp),
                            shadow = androidx.compose.ui.graphics.shadow.Shadow(
                                color = Color(0x40000000),              // #00000040
                                radius = 4.dp,                          // blur radius
                                offset = DpOffset(0.dp, 4.dp),          // x=0, y=4
                                spread = 0.dp                           // same as CSS spread
                            )
                        )
                )

                Box(
                    Modifier
                        .size(156.dp)
//                        .graphicsLayer {
//                            shape = RoundedCornerShape(radiusDp)
//                            clip = true
//                            shadowElevation = 2.dp.toPx()
//                            ambientShadowColor = Color(0x40000000)
//                            spotShadowColor = Color(0x40000000)
//                        }
                        .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                        .drawWithContent {
                            val mainColor = Color(0xB2CCFF)
                            drawContent()

                            drawRect(
                                color = Color.Green,
//                                cornerRadius = CornerRadius(radiusDp.toPx()),
                            )
                            drawRoundRect(
                                color = Color.Green,
                                cornerRadius = CornerRadius(radiusDp.toPx()),
                                blendMode = BlendMode.Clear,
                            )
//                            drawRoundRect(
//                                brush = Brush.verticalGradient(
//                                    listOf(
//                                        mainColor.copy(alpha = 0.3f),
//                                        mainColor.copy(alpha = 0.10f)
//                                    )
//                                ),
//                                cornerRadius = CornerRadius(radiusDp.toPx()),
//                                blendMode = BlendMode.SrcOver
//                            )
                        }
                )
            }
        }
    }
}