package com.martinezdputra.canvas.screen.ttt.chat_gpt

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun ChatGPTTicTacToeGame() {
    val board = remember { mutableStateListOf(
        CellState.Empty, CellState.Empty, CellState.Empty,
        CellState.Empty, CellState.Empty, CellState.Empty,
        CellState.Empty, CellState.Empty, CellState.Empty
    )}
    val currentPlayer = remember { mutableStateOf(CellState.Cross) }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    val cellWidth = size.width / 3
                    val cellHeight = size.height / 3
                    val row = (it.y / cellHeight).toInt()
                    val col = (it.x / cellWidth).toInt()
                    if (row in 0..2 && col in 0..2 && board[row * 3 + col] == CellState.Empty) {
                        board[row * 3 + col] = currentPlayer.value
                        currentPlayer.value = if (currentPlayer.value == CellState.Cross) CellState.Circle else CellState.Cross
                    }
                }
            }
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        // Draw background
        drawRect(
            color = Color.White,
            topLeft = Offset(0f, 0f),
            size = Size(canvasWidth, canvasHeight)
        )

        // Draw horizontal lines
        drawLine(
            color = Color.Black,
            start = Offset(0f, canvasHeight / 3),
            end = Offset(canvasWidth, canvasHeight / 3),
            strokeWidth = 4f
        )
        drawLine(
            color = Color.Black,
            start = Offset(0f, (canvasHeight / 3) * 2),
            end = Offset(canvasWidth, (canvasHeight / 3) * 2),
            strokeWidth = 4f
        )

        // Draw vertical lines
        drawLine(
            color = Color.Black,
            start = Offset(canvasWidth / 3, 0f),
            end = Offset(canvasWidth / 3, canvasHeight),
            strokeWidth = 4f
        )
        drawLine(
            color = Color.Black,
            start = Offset((canvasWidth / 3) * 2, 0f),
            end = Offset((canvasWidth / 3) * 2, canvasHeight),
            strokeWidth = 4f
        )

        // Draw Xs and Os
        board.forEachIndexed { index, state ->
            val row = index / 3
            val col = index % 3
            when (state) {
                CellState.Cross -> drawCross(row, col, canvasWidth, canvasHeight)
                CellState.Circle -> drawCircle(row, col, canvasWidth, canvasHeight)
                else -> {}
            }
        }
    }
}

fun DrawScope.drawCross(row: Int, col: Int, canvasWidth: Float, canvasHeight: Float) {
    val cellWidth = canvasWidth / 3
    val cellHeight = canvasHeight / 3
    val startX = col * cellWidth + 20
    val startY = row * cellHeight + 20
    val endX = (col + 1) * cellWidth - 20
    val endY = (row + 1) * cellHeight - 20

    drawLine(
        color = Color.Red,
        start = Offset(startX, startY),
        end = Offset(endX, endY),
        strokeWidth = 10f
    )
    drawLine(
        color = Color.Red,
        start = Offset(endX, startY),
        end = Offset(startX, endY),
        strokeWidth = 10f
    )

}

fun DrawScope.drawCircle(row: Int, col: Int, canvasWidth: Float, canvasHeight: Float) {
    val cellWidth = canvasWidth / 3
    val cellHeight = canvasHeight / 3
    val centerX = col * cellWidth + cellWidth / 2
    val centerY = row * cellHeight + cellHeight / 2
    val radius = cellWidth / 2 - 20

    drawCircle(
        color = Color.Blue,
        radius = radius,
        center = Offset(centerX, centerY),
        style = Stroke(10f)
    )
}

enum class CellState {
    Empty,
    Cross,
    Circle
}
