package com.sirelon.magicbuttons.designsystem.buttons

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sirelon.magicbuttons.designsystem.Typography
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun MagicGreenButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val bgColor = Color(0xFF43A980)
    val surfaceColor = Color(0xFF50B58D)

    val interaction = remember { MutableInteractionSource() }

    val pressed by interaction.collectIsPressedAsState()
    val inset by animateDpAsState(
        targetValue = if (pressed) 17.dp else 0.dp,
        animationSpec = tween(durationMillis = 75, easing = LinearEasing),
    )

    val topGradientColor by animateColorAsState(
        targetValue =
            if (pressed) Color.White.copy(alpha = 0.065f) else Color.White.copy(alpha = 0.13f),
        animationSpec = tween(durationMillis = 75, easing = LinearEasing),
    )
    val bottomGradientColor by animateColorAsState(
        targetValue =
            if (pressed) Color.White.copy(alpha = 0.15f) else Color.White.copy(alpha = 0.30f),
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
    ) {
        // shadow
        val buttonDepth = 17.dp
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(top = buttonDepth)
                .dropShadow(shape = RoundedCornerShape(outerRadius)) {
                    color = Color(0x29000000)
                    radius = 3.dp.toPx()
                    offset = Offset(0F, 3.dp.toPx())
                    spread = 0F
                    alpha = shadowAlpha
                },
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .drawBehind {
                    drawRoundRect(
                        color = bgColor,
                        topLeft = Offset(x = 0f, y = inset.toPx()),
                        cornerRadius = CornerRadius(outerRadius.toPx()),
                    )
                }
        )

        val innerCorner = 18.dp
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(bottom = buttonDepth)
                .graphicsLayer {
                    translationY = inset.toPx()
                }
                .clip(RoundedCornerShape(innerCorner))
                .padding(1.dp)
                .drawWithCache {
                    val cornerRadius = CornerRadius(innerCorner.toPx())

                    val bgGradient = Brush.verticalGradient(
                        colors = listOf(
                            topGradientColor,
                            Color.White.copy(alpha = 0f),
                            bottomGradientColor
                        )
                    )

                    onDrawBehind {
                        drawRoundRect(
                            color = surfaceColor,
                            cornerRadius = cornerRadius,
                        )

                        drawRoundRect(
                            brush = bgGradient,
                            blendMode = BlendMode.Screen,
                            cornerRadius = cornerRadius,
                        )
                    }
                }
                .border(innerCorner)
            ,
            contentAlignment = Alignment.Center,
        ) {
            ButtonText(text = text, shadowColor = bgColor)
        }
    }
}

private fun Modifier.border(cornerRadiusDp: Dp) = this.drawWithCache {
    val strokeWidth = 1.dp.toPx()

    val borderGradient = Brush.verticalGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.13f),
            Color.White.copy(alpha = 0f),
            Color.White.copy(alpha = 0.40f)
        )
    )

    val cornerRadius = CornerRadius(cornerRadiusDp.toPx())

    val outerRect = RoundRect(
        left = 0f,
        top = 0f,
        right = size.width,
        bottom = size.height,
        cornerRadius = cornerRadius
    )

    // Create the inner rounded rect (inset by border width on top/bottom only)
    val innerRect = RoundRect(
        left = 0f,
        top = strokeWidth,
        right = size.width,
        bottom = size.height - strokeWidth,
        cornerRadius = cornerRadius,
    )

    val outerPath = Path().apply { addRoundRect(outerRect) }
    val innerPath = Path().apply { addRoundRect(innerRect) }

    // Subtract inner from outer to get the border region
    val borderPath = Path().apply {
        op(outerPath, innerPath, PathOperation.Difference)
    }

    onDrawBehind {
        // Border
        drawPath(
            path = borderPath,
            brush = borderGradient
        )
    }
}

@Composable
private fun ButtonText(text: String, shadowColor: Color) {
    Text(
        text = text,
        style = TextStyle(
            fontWeight = FontWeight.W400,
            fontSize = 38.sp,
            textAlign = TextAlign.Center,
            fontFamily = Typography.ptSansFonts(),
            letterSpacing = 0.sp,
            color = Color(0xFFFFFFFF),
            shadow = with(LocalDensity.current) {
                Shadow(
                    color = shadowColor,
                    offset = Offset(x = 0f, y = 1.dp.toPx()),
                    blurRadius = 2.dp.toPx(),
                )
            }
        )
    )
}


@Preview
@Composable
fun MagicGreenButtonPreview() {
    Box(modifier = Modifier.fillMaxSize().safeContentPadding()) {
        MagicGreenButton(
            modifier = Modifier.size(
                width = 224.dp,
                height = 236.dp,
            ),
            text = "Старт",
            onClick = {},
        )
    }
}
