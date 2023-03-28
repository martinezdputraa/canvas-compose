package com.martinezdputra.canvas.screen.ttt.philipp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PhilippTicTacToeGameScreen() {
    var winningPlayer by remember {
        mutableStateOf<Player?>(null)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        var countdownSeconds by remember {
            mutableStateOf(0)
        }

        TicTacToe(
            modifier = Modifier.align(Alignment.Center),
            onNewRound = {
                winningPlayer = null
            },
            onPlayerWin = {
                winningPlayer = it
            },
            countdown = {
                countdownSeconds = it
            },
        )

        Column(
            modifier = Modifier
                .padding(top = 300.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (winningPlayer != null) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "Player ${winningPlayer!!.symbol} has won!",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (countdownSeconds > 0) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "A new round will begin in... $countdownSeconds")
            }
        }
    }
}
