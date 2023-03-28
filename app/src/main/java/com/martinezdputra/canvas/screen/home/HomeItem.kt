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
)
