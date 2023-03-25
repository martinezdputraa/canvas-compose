package com.martinezdputra.canvas.screen.clock

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ClockStyle(
    val radius: Dp = 100.dp,
    val secondsHandColor: Color = Color.Red,
    val minutesHandColor: Color = Color.Black,
    val hoursHandColor: Color = Color.Black,
    val secondsHandWidth: Dp = 2.dp,
    val minutesHandWidth: Dp = 3.dp,
    val hoursHandWidth: Dp = 5.dp,
    val secondsHandLength: Dp = 14.dp,
    val minutesHandLength: Dp = 20.dp,
    val hoursHandLength: Dp = 30.dp,
    val minuteIndicatorColor: Color = Color.LightGray,
    val fiveMinutesIndicatorColor: Color = Color.Black,
    val minuteIndicatorLength: Dp = 6.dp,
    val fiveMinutesIndicatorLength: Dp = 10.dp,
)
