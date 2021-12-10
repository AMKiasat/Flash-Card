package com.example.flashcard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashcard.activities.*
import com.example.flashcard.activities.CategoryActivity
import com.example.flashcard.objects.WordCard
import com.example.flashcard.objects.CategoryCard

val CARDS_LIST = ArrayList<WordCard>()
val CATEGORY_LIST = ArrayList<CategoryCard>()


sealed class ScreenRoute(val route: String) {
    object StartScreenRoute : ScreenRoute("start_screen")

    object AddWordScreenRoute : ScreenRoute("add_word_screen")

    object WordScreenRoute : ScreenRoute("word_screen")
    object CategoryScreenRoute : ScreenRoute("category_screen")
    object AddCategoryScreenRoute : ScreenRoute("add_category_screen")
}

@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.StartScreenRoute.route) {
        
        composable(route = ScreenRoute.StartScreenRoute.route){
            StartActivity(navController = navController)
        }
        composable(route = ScreenRoute.WordScreenRoute.route) {
            WordsActivity(navController = navController)
        }
        composable(route = ScreenRoute.AddWordScreenRoute.route) {
            AddWordActivity(navController = navController)
        }
        composable(route = ScreenRoute.CategoryScreenRoute.route) {
            CategoryActivity(navController = navController)
        }
        composable(route = ScreenRoute.AddCategoryScreenRoute.route) {
            AddCategoryActivity(navController = navController)
        }
    }
}




