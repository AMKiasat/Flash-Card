package com.example.flashcard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashcard.activities.AddCategoryActivity
import com.example.flashcard.activities.CategoriesActivity
import com.example.flashcard.objects.Card

val CARDS_LIST = ArrayList<Card>()


sealed class ScreenRoute(val route: String) {
    object MainScreenRoute : ScreenRoute("main_screen")
    object AddCategoryScreenRoute : ScreenRoute("add_category_screen")

    object CategoryScreenRoute : ScreenRoute("category_screen")
    object AddWordScreenRoute : ScreenRoute("add_word_screen")
}


@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.MainScreenRoute.route) {
        composable(route = ScreenRoute.MainScreenRoute.route) {
            CategoriesActivity(navController = navController)
        }
        composable(route = ScreenRoute.AddCategoryScreenRoute.route) {
            AddCategoryActivity(navController = navController)
        }
    }
}




