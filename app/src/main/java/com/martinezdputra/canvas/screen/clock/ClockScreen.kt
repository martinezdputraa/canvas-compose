package com.martinezdputra.canvas.screen.clock

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClockScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        val time = LocalTime.now()
        var delayInMillis by remember {
            mutableStateOf(1000)
        }
        var digitalClockString by remember {
            val formattedString = formatTime(
                hours = time.hour % 12,
                minutes = time.minute,
                seconds = time.second
            )
            mutableStateOf(formattedString)
        }
        val selectedItemColor = MaterialTheme.colors.primary
        val unselectedItemColor = MaterialTheme.colors.secondary

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = digitalClockString,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h2
        )

        Clock(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth(),
            initialSecond = time.second,
            initialMinute = time.minute,
            initialHour = time.hour % 12,
            delayInMillis = delayInMillis
        ) { hours, minutes, seconds ->
            digitalClockString = formatTime(
                hours = hours,
                minutes = minutes,
                seconds = seconds
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Delay in Milliseconds",
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(4.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.CenterHorizontally,
            )
        ) {
            item {
                Button(
                    onClick = { delayInMillis = 1000 },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (delayInMillis == 1000)
                            selectedItemColor
                        else
                            unselectedItemColor
                    )
                ) {
                    Text(text = "1000")
                }
            }
            item {
                Button(
                    onClick = { delayInMillis = 100 },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (delayInMillis == 100)
                            selectedItemColor
                        else
                            unselectedItemColor
                    )
                ) {
                    Text(text = "100")
                }
            }
            item {
                Button(
                    onClick = { delayInMillis = 10 },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (delayInMillis == 10)
                            selectedItemColor
                        else
                            unselectedItemColor
                    )
                ) {
                    Text(text = "10")
                }
            }
            item {
                Button(
                    onClick = { delayInMillis = 1 },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (delayInMillis == 1)
                            selectedItemColor
                        else
                            unselectedItemColor
                    )
                ) {
                    Text(text = "1")
                }
            }
        }
    }
}

private fun formatTime(hours: Int, minutes: Int, seconds: Int): String {
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}
