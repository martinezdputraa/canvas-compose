package com.martinezdputra.canvas

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.martinezdputra.canvas.screen.BasicShapesScreen
import com.martinezdputra.canvas.screen.click.ClickDetectorScreen
import com.martinezdputra.canvas.screen.clock.ClockScreen
import com.martinezdputra.canvas.screen.gender.GenderPicker
import com.martinezdputra.canvas.screen.gender.GenderPickerScreen
import com.martinezdputra.canvas.screen.home.HomeScreen
import com.martinezdputra.canvas.screen.images.ImageRevealScreen
import com.martinezdputra.canvas.screen.scale.ScaleScreen

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Route.HOME,
            ) {
                composable(Route.HOME) {
                    HomeScreen {
                        navController.navigate(it)
                    }
                }
                composable(Route.BALL_GAME) {
                    ClickDetectorScreen()
                }
                composable(Route.CLOCK) {
                    ClockScreen()
                }
                composable(Route.GENDER_PICKER) {
                    GenderPickerScreen()
                }
                composable(Route.IMAGE_REVEAL) {
                    ImageRevealScreen()
                }
                composable(Route.WEIGHT_SCALE) {
                    ScaleScreen()
                }
                composable(Route.BASIC_SHAPES) {
                    BasicShapesScreen()
                }
            }
        }
    }
}


