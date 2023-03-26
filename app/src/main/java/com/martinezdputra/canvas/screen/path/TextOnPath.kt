package com.martinezdputra.canvas.screen.path

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp

@Composable
fun TextOnPath() {
    val path = Path().apply {
        moveTo(200f, 800f)
        quadTo(
            600f,
            400f,
            1000f,
            800f,
        )
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawContext.canvas.nativeCanvas.apply {
            drawTextOnPath(
                "This is my text drawn on canvas!",
                path,
                0f,
                -60f,
                Paint().apply {
                    color = Color.RED
                    textSize = 50f
                    textAlign = Paint.Align.CENTER
                }
            )
        }
        drawPath(
            path = path.asComposePath(),
            color = androidx.compose.ui.graphics.Color.Black,
            style = Stroke(
                width = 3.dp.toPx(),
                cap = StrokeCap.Round,
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(50f, 30f)
                )
            )
        )
    }
}
