package com.martinezdputra.canvas.screen.clock

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Clock(
    modifier: Modifier = Modifier,
    style: ClockStyle = ClockStyle(),
    initialSecond: Int = 0,
    initialMinute: Int = 0,
    initialHour: Int = 0,
) {
    var center by remember {
        mutableStateOf(Offset.Zero)
    }
    var seconds by remember {
        mutableStateOf(initialSecond)
    }
    var minutes by remember {
        mutableStateOf(initialMinute)
    }
    var hours by remember {
        mutableStateOf(initialHour)
    }
    LaunchedEffect(key1 = true) {
        while (true) {
            delay(1000)
            seconds++
            if(seconds >= 60) {
                seconds = 0
                minutes++
            }
            if(minutes >= 60) {
                minutes = 0
                hours++
            }
            if(hours >= 12) {
                hours = 0
            }
        }
    }
    Canvas(modifier = modifier) {
        center = this.center
        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                center.x,
                center.y,
                style.radius.toPx(),
                Paint().apply {
                    color = Color.WHITE
                    setStyle(Paint.Style.FILL)
                    setShadowLayer(
                        60F,
                        0f,
                        0f,
                        Color.argb(50, 0, 0, 0)
                    )
                }
            )
        }
        // Draw lines
        for(i in 0..59) {
            val angleInRad = (360f / 60f) * i * (PI.toFloat() / 180f)
            val lineType = when {
                i % 5 == 0 -> LineType.FiveStep
                else -> LineType.Normal
            }
            val lineLength = when(lineType) {
                LineType.FiveStep -> style.fiveMinutesIndicatorLength
                LineType.Normal -> style.minuteIndicatorLength
            }.toPx()
            val lineColor = when(lineType) {
                LineType.FiveStep -> style.fiveMinutesIndicatorColor
                LineType.Normal -> style.minuteIndicatorColor
            }
            val lineStart = Offset(
                x = style.radius.toPx() * cos(angleInRad) + center.x,
                y = style.radius.toPx() * sin(angleInRad) + center.y,
            )
            val lineEnd = Offset(
                x = (style.radius.toPx() - lineLength) * cos(angleInRad) + center.x,
                y = (style.radius.toPx() - lineLength) * sin(angleInRad) + center.y,
            )
            drawLine(
                color = lineColor,
                start = lineStart,
                end = lineEnd,
                strokeWidth = 1.dp.toPx(),
            )
        }

        rotate(degrees = (360f / 60f) * seconds) {
            drawLine(
                color = style.secondsHandColor,
                start = center,
                end = Offset(
                    x = center.x,
                    y = center.y - style.radius.toPx() + style.secondsHandLength.toPx(),
                ),
                strokeWidth = style.secondsHandWidth.toPx(),
                cap = StrokeCap.Round
            )
        }
        rotate(degrees = (360f / 60f) * minutes) {
            drawLine(
                color = style.minutesHandColor,
                start = center,
                end = Offset(
                    x = center.x,
                    y = center.y - style.radius.toPx() + style.minutesHandLength.toPx(),
                ),
                strokeWidth = style.minutesHandWidth.toPx(),
                cap = StrokeCap.Round
            )
        }
        rotate(degrees = (360f / 12f) * hours) {
            drawLine(
                color = style.hoursHandColor,
                start = center,
                end = Offset(
                    x = center.x,
                    y = center.y - style.radius.toPx() + style.hoursHandLength.toPx(),
                ),
                strokeWidth = style.hoursHandWidth.toPx(),
                cap = StrokeCap.Round
            )
        }
    }
}
