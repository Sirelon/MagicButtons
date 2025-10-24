package com.sirelon.magicbuttons.designsystem.buttons

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sirelon.magicbuttons.designsystem.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MagicBlueButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    animationDuration: Int = 150,
    animationEasing: Easing = EaseOut,
    interaction: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val radiusDp = 16.dp
    val shape = RoundedCornerShape(radiusDp)
    val mainBgColor = Color(0xB2CCFF)

    val pressed by interaction.collectIsPressedAsState()

    val transition = updateTransition(targetState = pressed, label = "BlueButtonPressedAnimation")

    val transitionSpecFunc: @Composable Transition.Segment<Boolean>.() -> FiniteAnimationSpec<Float> =
        { tween(animationDuration, easing = animationEasing) }

    val outlineBorderAlpha by transition.animateFloat(
        label = "outlineBorderAlpha",
        transitionSpec = transitionSpecFunc,
        targetValueByState = { if (it) 0.3f else 0f }
    )

    val innerShadowAlpha by transition.animateFloat(
        label = "innerShadow",
        transitionSpec = transitionSpecFunc,
        targetValueByState = { if (it) 0.25f else 0f }
    )

    val pressedShadow by transition.animateFloat(
        label = "pressedShadow",
        transitionSpec = transitionSpecFunc,
        targetValueByState = { if (it) 1f else 0f }
    )

    val outerShadow1Alpha by transition.animateFloat(
        label = "outerShadow1Alpha",
        transitionSpec = transitionSpecFunc,
        targetValueByState = { if (it) 0f else 0.25f }
    )

    val outerShadow2Alpha by transition.animateFloat(
        label = "outerShadow2Alpha",
        transitionSpec = transitionSpecFunc,
        targetValueByState = { if (it) 0f else 0.3f }
    )

    val bgColor1 by transition.animateColor(
        label = "bgColor1",
        transitionSpec = { tween(animationDuration, easing = animationEasing) },
        targetValueByState = {
            if (it) mainBgColor.copy(alpha = 0.15f) else mainBgColor.copy(alpha = 0.3f)
        }
    )

    val bgColor2 by transition.animateColor(
        label = "bgColor2",
        transitionSpec = { tween(animationDuration, easing = animationEasing) },
        targetValueByState = {
            if (it) mainBgColor.copy(alpha = 0.2f) else mainBgColor.copy(alpha = 0.1f)
        }
    )

    val translationY by transition.animateDp(
        label = "translationY",
        transitionSpec = { tween(animationDuration, easing = animationEasing) },
        targetValueByState = { if (it) 2.dp else 0.dp },
    )

    Box(
        modifier = modifier
            .semantics {
                role = Role.Button
            }
            .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
            // Padding here needed to show shadows properly
            .padding(8.dp)
            .graphicsLayer {
                this.translationY = translationY.toPx()
            }
            .clickable(
                enabled = true,
                onClick = onClick,
                indication = null,
                interactionSource = interaction,
            )
            .dropShadow(shape = shape) {
                this.radius = 4.dp.toPx()
                this.offset = Offset(x = 0f, y = 4.dp.toPx())
                this.color = Color(0xFF000000)
                this.alpha = outerShadow1Alpha
            }
            .dropShadow(shape = shape) {
                this.radius = 4.dp.toPx()
                this.spread = -2.dp.toPx()
                this.offset = Offset(x = 0f, y = 4.dp.toPx())

                this.color = Color(0xFF000000)
                this.alpha = outerShadow2Alpha
            }
            .dropShadow(shape = shape) {
                this.color = Color(0xFF0D0F1A)
                this.alpha = pressedShadow
            }
            .drawWithCache {
                val strokeWidth = 1.dp.toPx()
                val bgGradient = Brush.verticalGradient(
                    colors = listOf(
                        bgColor1,
                        bgColor2,
                    )
                )

                val borderGradient = Brush.verticalGradient(
                    colors = listOf(
                        mainBgColor.copy(alpha = 0.1f),
                        mainBgColor.copy(alpha = 0.05f)
                    )
                )

                val cornerRadius = CornerRadius(radiusDp.toPx())
                val outlineBorderSize = Size(size.width + strokeWidth, size.height + strokeWidth)
                val borderStroke = Stroke(width = strokeWidth)
                val borderOffset = Offset(-strokeWidth / 2, -strokeWidth / 2)
                val borderColor = Color(0xFF000000).copy(alpha = outlineBorderAlpha)

                onDrawBehind {
                    // clear shadow colors inside button
                    drawRoundRect(
                        color = Color.Transparent,
                        cornerRadius = cornerRadius,
                        blendMode = BlendMode.Clear
                    )

                    // Dark Border that is visible when btn is in pressed state
                    drawRoundRect(
                        color = borderColor,
                        topLeft = borderOffset,
                        size = outlineBorderSize,
                        cornerRadius = cornerRadius,
                        style = borderStroke
                    )

                    // Button's Background
                    drawRoundRect(
                        brush = bgGradient,
                        blendMode = BlendMode.Hardlight,
                        cornerRadius = cornerRadius,
                    )

                    // Gradient Border
                    drawRoundRect(
                        brush = borderGradient,
                        style = borderStroke,
                        cornerRadius = cornerRadius,
                    )
                }
            }
            .innerShadow(shape = shape) {
                this.offset = Offset(x = 0f, y = 2.dp.toPx())
                this.radius = 1.dp.toPx()
                this.alpha = innerShadowAlpha
                this.color = Color(0xFF0D1626)
            }
    ) {
        ButtonText(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 44.5.dp, vertical = 15.dp),
            text = text,
        )
    }
}

@Composable
private fun ButtonText(text: String, modifier: Modifier) {
    Text(
        modifier = modifier,
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
            }
        }
    }
}
