package com.example.flashcard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashcard.activities.AddWordActivity
import com.example.flashcard.activities.WordsActivity
import com.example.flashcard.activities.StartActivity
import com.example.flashcard.objects.Card

val CARDS_LIST = ArrayList<Card>()


sealed class ScreenRoute(val route: String) {
    object StartScreenRoute : ScreenRoute("start_screen")

    object AddCategoryScreenRoute : ScreenRoute("add_category_screen")

    object CategoryScreenRoute : ScreenRoute("category_screen")
    object AddWordScreenRoute : ScreenRoute("add_word_screen")
}


sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Categories : NavigationItem("categories", R.drawable.ic_categories, "Categories")
}

@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.StartScreenRoute.route) {
        
        composable(route = ScreenRoute.StartScreenRoute.route){
            StartActivity(navController = navController)
        }
        
        composable(route = ScreenRoute.CategoryScreenRoute.route) {
            WordsActivity(navController = navController)
        }
        composable(route = ScreenRoute.AddCategoryScreenRoute.route) {
            AddWordActivity(navController = navController)
        }
    }
}




