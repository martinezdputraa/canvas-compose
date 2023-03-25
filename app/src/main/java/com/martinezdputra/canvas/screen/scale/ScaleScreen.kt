package com.martinezdputra.canvas.screen.scale

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScaleScreen() {
    var weight by remember {
        mutableStateOf(80)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Scale(
            style = ScaleStyle(
                scaleWidth = 150.dp,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.BottomCenter)
        ) { newWeight ->
            weight = newWeight
        }
    }
}
