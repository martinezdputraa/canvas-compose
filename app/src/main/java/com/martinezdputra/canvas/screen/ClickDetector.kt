package com.martinezdputra.canvas.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.random.Random

@Composable
fun ClickDetector() {
    var score by remember {
        mutableStateOf(0)
    }
    var isTimerRunning by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Score: $score",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Button(onClick = {
                isTimerRunning = !isTimerRunning
                score = 0
            }) {
                Text(text = if(isTimerRunning) "Reset" else "Start")
            }
            CountdownTimer(
                isTimerRunning = isTimerRunning
            ) {
                isTimerRunning = false
            }
        }
        BallClicker(
            enabled = isTimerRunning
        ) {
            score++
        }
    }
}

@Composable
fun CountdownTimer(
    time: Int = 30000,
    isTimerRunning: Boolean = false,
    onTimerEnd: () -> Unit = {}
) {
    var currentTime by remember {
        mutableStateOf(time)
    }
    LaunchedEffect(
        key1 = currentTime,
        key2 = isTimerRunning,
    ) {
        if(!isTimerRunning) {
            currentTime = time
            return@LaunchedEffect
        }
        if(currentTime > 0) {
            delay(1000)
            currentTime -= 1000
        } else {
            onTimerEnd()
        }
    }
    Text(
        text = "Timer: ${(currentTime / 1000)}",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun BallClicker(
    radius: Float = 100f,
    enabled: Boolean = false,
    onBallClick: () -> Unit = {},
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        var ballPosition by remember {
            mutableStateOf(randomOffset(
                radius = radius,
                width = constraints.maxWidth,
                height = constraints.maxHeight,
            ))
        }
        var ballColor by remember {
            mutableStateOf(Color.Red)
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(enabled) {
                    if(!enabled) {
                        return@pointerInput
                    }
                    detectTapGestures { tapOffset ->
                        val distance = sqrt(
                            (tapOffset.x - ballPosition.x).pow(2) +
                            (tapOffset.y - ballPosition.y).pow(2)
                        )
                        if(distance <= radius) {
                            ballColor = Color(android.graphics.Color.parseColor(randomColorHexString()))
                            ballPosition = randomOffset(
                                radius = radius,
                                width = constraints.maxWidth,
                                height = constraints.maxHeight,
                            )
                            onBallClick()
                        }
                    }
                }
        ) {
            drawCircle(
                color = ballColor,
                radius = radius,
                center = ballPosition,
            )
        }
    }
}

private fun randomOffset(radius: Float, width: Int, height: Int): Offset {
    return Offset(
        x = Random.nextInt(radius.roundToInt(), width - radius.roundToInt()).toFloat(),
        y = Random.nextInt(radius.roundToInt(), height - radius.roundToInt()).toFloat(),
    )
}

private fun randomColorHexString(): String {
    val r = "${randomHex()}${randomHex()}"
    val g = "${randomHex()}${randomHex()}"
    val b = "${randomHex()}${randomHex()}"
    return "#$r$g$b"
}

private fun randomHex(): String {
    return when(val value = Random.nextInt(0, 16)) {
        10 -> "A"
        11 -> "B"
        12 -> "C"
        13 -> "D"
        14 -> "E"
        15 -> "F"
        else -> value.toString()
    }
}
