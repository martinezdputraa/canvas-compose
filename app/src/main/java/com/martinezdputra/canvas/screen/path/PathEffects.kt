package com.martinezdputra.canvas.screen.path

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect.Companion.chainPathEffect
import androidx.compose.ui.graphics.PathEffect.Companion.cornerPathEffect
import androidx.compose.ui.graphics.PathEffect.Companion.dashPathEffect
import androidx.compose.ui.graphics.PathEffect.Companion.stampedPathEffect
import androidx.compose.ui.graphics.StampedPathEffectStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun PathEffects() {
    val infiniteTransition = rememberInfiniteTransition()
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 10000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 60000,
                easing = LinearEasing,
            )
        )
    )
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(100f, 100f)
            cubicTo(
                x1 = 100f,
                y1 = 300f,
                x2 = 600f,
                y2 = 700f,
                x3 = 600f,
                y3 = 1100f,
            )
        }
        drawPath(
            path = path,
            color = Color.Blue,
            style = Stroke(
                width = 5.dp.toPx(),
                pathEffect = dashPathEffect(
                    intervals = floatArrayOf(50f, 30f),
                    phase = phase,
                )
            )
        )

        val path1 = Path().apply {
            moveTo(200f, 100f)
            cubicTo(
                x1 = 200f,
                y1 = 300f,
                x2 = 700f,
                y2 = 700f,
                x3 = 700f,
                y3 = 1100f,
            )
            lineTo(800f, 800f)
            lineTo(1000f, 1100f)
        }
        drawPath(
            path = path1,
            color = Color.Red,
            style = Stroke(
                width = 5.dp.toPx(),
                pathEffect = cornerPathEffect(
                    radius = 1000f,
                )
            )
        )

        val path2 = Path().apply {
            moveTo(50f, 1400f)
            lineTo(200f, 1200f)
            lineTo(350f, 1400f)
            lineTo(500f, 1200f)
            lineTo(650f, 1400f)
            lineTo(800f, 1200f)
            lineTo(950f, 1400f)
        }
        val oval = Path().apply {
            addOval(
                Rect(
                    topLeft = Offset.Zero,
                    bottomRight = Offset(
                        40f, 10f
                    )
                )
            )
        }

        drawPath(
            path = path2,
            color = Color.Black,
            style = Stroke(
                width = 5.dp.toPx(),
                pathEffect = stampedPathEffect(
                    shape = oval,
                    advance = 60f,
                    phase = phase,
                    style = StampedPathEffectStyle.Translate
                )
            )
        )

        val path3 = Path().apply {
            moveTo(50f, 1700f)
            lineTo(200f, 1500f)
            lineTo(350f, 1700f)
            lineTo(500f, 1500f)
            lineTo(650f, 1700f)
            lineTo(800f, 1500f)
            lineTo(950f, 1700f)
        }

        drawPath(
            path = path3,
            color = Color.Magenta,
            style = Stroke(
                width = 5.dp.toPx(),
                pathEffect = chainPathEffect(
                    outer = stampedPathEffect(
                        shape = oval,
                        advance = 60f,
                        phase = phase,
                        style = StampedPathEffectStyle.Morph
                    ),
                    inner = dashPathEffect(
                        intervals = floatArrayOf(100f, 100f)
                    )
                )
            )
        )
    }
}
