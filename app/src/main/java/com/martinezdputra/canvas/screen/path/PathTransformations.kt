package com.martinezdputra.canvas.screen.path

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp

@Composable
fun PathTransformations() {
    val rotateSquare = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        rotateSquare.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 5000
            ),
        )
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        val circle = Path().apply {
            addOval(
                Rect(
                    center = Offset(400f, 400f),
                    radius = 300f
                )
            )
        }
        drawPath(
            path = circle,
            color = Color.Black,
            style = Stroke(width = 5.dp.toPx())
        )
        clipPath(
            path = circle
        ) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(400f, 400f),
                size = Size(400f, 400f)
            )
        }
//        translate(left = rotateSquare.value * 300f, top = rotateSquare.value * 300f) {
//            rotate(rotateSquare.value * 360f, Offset(100f, 100f)) {
//                scale(0.5f, Offset(200f, 200f)) {
//                    drawRect(
//                        color = Color.Red,
//                        topLeft = Offset(100f, 100f),
//                        size = Size(200f, 200f)
//                    )
//                }
//            }
//        }
    }
}
