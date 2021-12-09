package com.example.flashcard

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object AddWordScreen : Screen("add_world_screen")
}
