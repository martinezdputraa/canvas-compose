package com.martinezdputra.canvas.screen.clock

sealed interface LineType {
    object Normal: LineType
    object FiveStep: LineType
}
