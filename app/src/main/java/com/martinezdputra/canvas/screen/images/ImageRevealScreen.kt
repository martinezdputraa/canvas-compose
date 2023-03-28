package com.martinezdputra.canvas.screen.images

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.martinezdputra.canvas.R

@Composable
fun ImageRevealScreen() {
    var center by remember {
        mutableStateOf(Offset.Zero)
    }

    var cursorPosition by remember {
        mutableStateOf(Offset.Zero)
    }

    val maxwell = ImageBitmap.imageResource(id = R.drawable.maxwell)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(true) {
                detectDragGestures { cursor, _ ->
                    cursorPosition = cursor.position
                }
            }
    ) {
        center = this.center
        val imageWidth = size.width.toInt()
        val imageHeight = imageWidth

        val imageOffsetX = center.x - imageWidth / 2
        val imageOffsetY = center.y - imageWidth / 2

        drawImage(
            image = maxwell,
            dstOffset = IntOffset(
                x = (imageOffsetX).toInt(),
                y = (imageOffsetY).toInt(),
            ),
            dstSize = IntSize(
                width = imageWidth,
                height = imageHeight,
            ),
            colorFilter = ColorFilter.tint(
                color = Color.Black,
                blendMode = BlendMode.Color
            )
        )

        val circlePath = Path().apply {
            addArc(
                oval = Rect(cursorPosition, 300f),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 360f
            )
        }

        clipPath(circlePath, clipOp = ClipOp.Intersect) {
            drawImage(
                image = maxwell,
                dstSize = IntSize(
                    imageWidth,
                    imageHeight
                ),
                dstOffset = IntOffset(
                    imageOffsetX.toInt(),
                    imageOffsetY.toInt()
                ),
            )
        }
    }
}
