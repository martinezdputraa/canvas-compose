package com.martinezdputra.canvas.screen.home

import com.martinezdputra.canvas.Route

data class HomeItem(
    val label: String,
    val route: String,
)

val homeItems = listOf(
    HomeItem(
        label = "Ball Game",
        route = Route.BALL_GAME,
    ),
    HomeItem(
        label = "Clock",
        route = Route.CLOCK,
    ),
    HomeItem(
        label = "Gender Picker",
        route = Route.GENDER_PICKER,
    ),
    HomeItem(
        label = "Image Reveal",
        route = Route.IMAGE_REVEAL,
    ),
    HomeItem(
        label = "Weight Scale",
        route = Route.WEIGHT_SCALE,
    ),
    HomeItem(
        label = "Basic Shapes",
        route = Route.BASIC_SHAPES,
    ),
    HomeItem(
        label = "Chat GPT Generated TicTacToe Game",
        route = Route.CHAT_GPT_TTT,
    ),
    HomeItem(
        label = "Philipp's TicTacToe Game",
        route = Route.PHILIPP_TTT,
    ),
)
