package com.martinezdputra.canvas.screen.path

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun BasicPath() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(100f, 100f)
            lineTo(100f, 500f)
            lineTo(500f, 500f)
            quadraticBezierTo(
                x1 = 800f,
                y1 = 300f,
                x2 = 500f,
                y2 = 100f,
            )
            close()
        }
        val path2 = Path().apply {
            moveTo(100f, 600f)
            lineTo(100f, 1000f)
            lineTo(500f, 1000f)
            cubicTo(
                x1 = 800f,
                y1 = 1000f,
                x2 = 800f,
                y2 = 600f,
                x3 = 500f,
                y3 = 600f,
            )
            close()
        }

        val path3 = Path().apply {
            moveTo(100f, 1100f)
            lineTo(100f, 1600f)
            lineTo(500f, 1600f)
        }

        drawPath(
            path = path,
            color = Color.Blue,
            style = Stroke(width = 5.dp.toPx())
        )

        drawPath(
            path = path2,
            color = Color.Red,
            style = Stroke(width = 5.dp.toPx())
        )

        drawPath(
            path = path3,
            color = Color.Magenta,
            style = Stroke(
                width = 15.dp.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Miter,
                miter = 0f,
            )
        )
    }
}
