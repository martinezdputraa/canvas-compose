package com.martinezdputra.canvas.screen.gender

sealed class Gender(val name: String) {
    object Male: Gender("Male")
    object Female: Gender("Female")
}
