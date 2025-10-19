package com.sirelon.magicbuttons.designsystem.buttons

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key.Companion.P
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun MagicGreenButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val backBgColor = Color(0xFF43A980)

    val interaction = remember { MutableInteractionSource() }

    val pressed by interaction.collectIsPressedAsState()
    val inset by animateDpAsState(
        targetValue = if (pressed) 18.dp else 0.dp,
        animationSpec = tween(durationMillis = 75, easing = LinearEasing),
    )

    val shadowAlpha by animateFloatAsState(if (pressed) 0f else 1f)

    val outerRadius = 19.dp

    Box(
        modifier = modifier
            .semantics {
                role = Role.Button
            }
            .clickable(
                enabled = enabled,
                onClick = onClick,
                indication = null,
                interactionSource = interaction,
            )
            // TODO: Size.
            .size(
                width = 224.dp,
                height = 236.dp,
            )
    ) {
        // shadow
        val buttonDepth = 17.dp
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(top = buttonDepth)
                .dropShadow(shape = RoundedCornerShape(outerRadius)) {
                    color = Color(0x29000000)
                    radius = 3f
                    offset = Offset(0F, 3F)
                    spread = 0F
                    alpha = shadowAlpha
                },
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .drawBehind {
                    drawRoundRect(
                        color = backBgColor,
                        topLeft = Offset(x = 0f, y = inset.toPx()),
                        cornerRadius = CornerRadius(outerRadius.toPx()),
                    )
                }
        )

        val innerCorner = 18.dp
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = buttonDepth)
                .graphicsLayer {
                    translationY = inset.toPx()
                }
                .clip(RoundedCornerShape(innerCorner))
                .padding(1.dp)
                .drawWithCache {
                    val strokeWidth = 1.dp.toPx()

                    val borderGradient = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.13f),
                            Color.White.copy(alpha = 0f),
                            Color.White.copy(alpha = 0.40f)
                        )
                    )

                    val bgGradient = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.13f),
                            Color.White.copy(alpha = 0f),
                            Color.White.copy(alpha = 0.30f)
                        )
                    )
                    val cornerRadius = CornerRadius(innerCorner.toPx())

                    onDrawBehind {
                        drawBorder(
                            strokeWidth = strokeWidth,
                            cornerRadius = cornerRadius,
                            brush = borderGradient
                        )
                        drawAlGradient(
                            brush = bgGradient,
                            cornerRadius = cornerRadius,
                        )
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            ButtonText(text = text, shadowColor = backBgColor)
        }
    }
}

private fun DrawScope.drawBorder(
    strokeWidth: Float,
    cornerRadius: CornerRadius,
    brush: Brush
) {
    val rr = RoundRect(
        top = 0f,
        bottom = size.height,
        left = -strokeWidth / 2,
        right = size.width + strokeWidth / 2,
        cornerRadius = cornerRadius
    )
    val path = Path().apply { addRoundRect(rr) }

    val clipRect = RoundRect(
        top = 0f,
        bottom = size.height,
        left = 0f,
        right = size.width,
        cornerRadius = cornerRadius
    )
    val clipPath = Path().apply { addRoundRect(clipRect) }

    clipPath(clipPath) {
        drawPath(
            path = path,
            brush = brush,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
    }
}

private fun DrawScope.drawAlGradient(brush: Brush, cornerRadius: CornerRadius) {
    drawRoundRect(
        brush = brush,
        blendMode = BlendMode.Screen,
        cornerRadius = cornerRadius,
    )
}

@Composable
private fun ButtonText(text: String, shadowColor: Color) {
    // TODO: PT Sans font https://fonts.google.com/specimen/PT+Sans
    Text(
        text = text,
        style = TextStyle(
            fontWeight = FontWeight.W400,
            fontSize = 38.sp,
            textAlign = TextAlign.Center,
            letterSpacing = 0.sp,
            color = Color(0xFFFFFFFF),
            shadow = Shadow(
                color = shadowColor,
                offset = Offset(0f, 1f),
                blurRadius = 2f,
            )
        )
    )
}


@Preview
@Composable
fun MagicGreenButtonPreview() {
    Box(modifier = Modifier.fillMaxSize().safeContentPadding()) {
        MagicGreenButton(text = "Старт", onClick = {})
    }
}
