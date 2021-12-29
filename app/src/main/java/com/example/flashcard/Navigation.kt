package com.example.flashcard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashcard.activities.*
import com.example.flashcard.objects.CategoryCard
import com.example.flashcard.objects.WordCard

val CARDS_LIST = ArrayList<WordCard>()
val CATEGORY_LIST = ArrayList<CategoryCard>()


sealed class ScreenRoute(val route: String) {
    object StartScreenRoute : ScreenRoute("start_screen")
    object LoginScreenRoute : ScreenRoute("login_screen")

    object AddWordScreenRoute : ScreenRoute("add_word_screen")
    object SplashImageRoute :ScreenRoute("splash_image")
    object WordScreenRoute : ScreenRoute("word_screen")
    object CategoryScreenRoute : ScreenRoute("category_screen")
    object AddCategoryScreenRoute : ScreenRoute("add_category_screen")
    object SearchScreenRoute : ScreenRoute("Search_screen")
}

@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.SplashImageRoute.route) {
        composable(ScreenRoute.SplashImageRoute.route ){
            SplashImage(navController = navController)

        }

        composable(route = ScreenRoute.StartScreenRoute.route) {
            StartActivity(navController = navController)
        }
        composable(route = ScreenRoute.LoginScreenRoute.route) {
            LoginActivity(navController = navController)
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
        composable(route = ScreenRoute.SearchScreenRoute.route) {
            SearchActivity(navController = navController)
        }
    }
}




