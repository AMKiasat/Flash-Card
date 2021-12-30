package com.example.flashcard

sealed class NavigationBarItems(var route: String, var icon: Int, var title: String) {
    object Home : NavigationBarItems(ScreenRoute.WordScreenRoute.route, R.drawable.ic_home, "Home")
    object Categories : NavigationBarItems(ScreenRoute.CategoryScreenRoute.route, R.drawable.ic_categories, "Categories")
    object Search : NavigationBarItems(ScreenRoute.SearchScreenRoute.route, R.drawable.ic_search, "Search")
}