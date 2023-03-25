package com.martinezdputra.canvas.screen.clock

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClockScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        val time = LocalTime.now()
        Clock(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            initialSecond = time.second,
            initialMinute = time.minute,
            initialHour = time.hour % 12,
        )
    }
}
