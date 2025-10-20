package com.sirelon.magicbuttons.designsystem.buttons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
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
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun Test() = Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
) {

    val radiusDp = 16.dp

    val interaction = remember { MutableInteractionSource() }

    val pressed by interaction.collectIsPressedAsState()
    val mainColor = Color(0xB2CCFF)

    Box(
        modifier = Modifier
            .matchParentSize()
            .background(color = Color(0xD10D1A35))
            .blur(50.dp)
    )


    if (true) {

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

        return@Box
    }



    Box(
        // TODO: Size
        modifier = Modifier
            .size(width = 150.dp, height = 60.dp)
            .clickable(
                enabled = true,
                onClick = { },
                indication = null,
                interactionSource = interaction,
            )
            .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
            .padding(8.dp)
//            .dropShadow(shape = RoundedCornerShape(radiusDp)) {
//                this.radius = 4.dp.toPx()
//                this.offset = Offset(x = 0f, y = 4.dp.toPx())
//                this.color = Color(0xFF000000)
//                this.alpha = 0.25f
////                this.color = Color.Red
//            }
//            .dropShadow(shape = RoundedCornerShape(radiusDp)) {
//                this.spread = -2.dp.toPx()
//                this.radius = 4.dp.toPx()
//                this.offset = Offset(x = 0f, y = 4.dp.toPx())
////                this.color = Color.Green
//
//                this.color = Color(0xFF000000)
//                this.alpha = 0.3f
//            }
//            .drawBehind {
//                drawRoundRect(
//                    color = Color.Transparent,
//                    cornerRadius = CornerRadius(radiusDp.toPx()),
//                    blendMode = BlendMode.Clear
//                )
//            }
            .dropShadow(RoundedCornerShape(radiusDp)) {
                color = Color(0xFF0D0F1A)
            }
            .drawBehind {
                drawRoundRect(
                    color = Color.Transparent,
                    cornerRadius = CornerRadius(radiusDp.toPx()),
                    blendMode = BlendMode.Clear
                )
            }
//            .border(
//                width = 1.dp,
//                color = Color(0x4D000000),
//                shape = RoundedCornerShape(radiusDp)
//            )
            .drawWithCache {
                val borderColor = Color(0x4D000000)
                val strokeWidth = 1.dp.toPx()
                onDrawBehind {
                    drawRoundRect(
                        color = borderColor,
                        cornerRadius = CornerRadius(radiusDp.toPx()),
                        style = Stroke(strokeWidth),
                        size = size.copy(size.width + strokeWidth, size.height + strokeWidth)
                    )
                }
            }
            .innerShadow(RoundedCornerShape(radiusDp)) {
                this.radius = 1.dp.toPx()
                this.offset = Offset(0f, y = 2.dp.toPx())
                this.color = Color(0xFF0D1626)
                this.alpha = 0.25f
            }

//            .blueBg(radiusDp)
    ) {

        Box(modifier = Modifier.matchParentSize().drawWithCache {
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
        })

        ButtonText("Cancel")
    }
}
