package com.example.flashcard.objects

import com.example.flashcard.R
import com.example.flashcard.ScreenRoute

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem(ScreenRoute.WordScreenRoute.route, R.drawable.ic_home, "Home")
    object Categories : NavigationItem(ScreenRoute.CategoryScreenRoute.route, R.drawable.ic_categories, "Categories")
    object Search : NavigationItem(ScreenRoute.SearchScreenRoute.route, R.drawable.ic_search, "Search")
}