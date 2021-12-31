package com.example.flashcard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashcard.activities.*


sealed class ScreenRoute(val route: String) {
    object StartScreenRoute : ScreenRoute("start_screen")
    object LoginScreenRoute : ScreenRoute("login_screen")
    object AddWordScreenRoute : ScreenRoute("add_word_screen")
    object SplashImageRoute : ScreenRoute("splash_image")
    object WordScreenRoute : ScreenRoute("word_screen")
    object CategoryScreenRoute : ScreenRoute("category_screen")
    object AddCategoryScreenRoute : ScreenRoute("add_category_screen")
    object SearchScreenRoute : ScreenRoute("search_screen")
    object InsideCategoryScreenRoute : ScreenRoute("inside_category_screen")
    object AnalysisScreenRoute : ScreenRoute("analysis_screen")
    object HomeScreenRoute : ScreenRoute("home_screen")
    object ContactUsScreenRoute : ScreenRoute("contact_us_screen")
    object SettingsScreenRoute : ScreenRoute("settings_screen")
}

@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.StartScreenRoute.route) {
//        composable(ScreenRoute.SplashImageRoute.route) {
//            SplashImage(navController = navController)
//        }
        composable(route = ScreenRoute.StartScreenRoute.route) {
            StartActivity(navController = navController)
        }
        composable(route = ScreenRoute.LoginScreenRoute.route) {
            LoginActivity(navController = navController)
        }
        composable(route = ScreenRoute.WordScreenRoute.route) {
            WordsActivity(navController = navController)
        }
        composable(route = ScreenRoute.AddWordScreenRoute.route + "/{category_name}") { navBackStack ->
            val category_name = navBackStack.arguments?.getString("category_name")
            AddWordActivity(navController = navController, category_name = category_name)
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
        composable(route = ScreenRoute.AnalysisScreenRoute.route) {
            AnalysisActivity(navController = navController)
        }
        composable(route = ScreenRoute.HomeScreenRoute.route) {
            HomeActivity(navController = navController)
        }
        composable(route = ScreenRoute.SettingsScreenRoute.route) {
            SettingsActivity(navController = navController)
        }
        composable(route = ScreenRoute.ContactUsScreenRoute.route) {
            ContactUsActivity(navController = navController)
        }
        composable(route = ScreenRoute.InsideCategoryScreenRoute.route+"/{category_name}") { navBackStack ->
            val category_name = navBackStack.arguments?.getString("category_name")
            InsideCategoryActivity(navController = navController ,category_name)
        }
    }
}




