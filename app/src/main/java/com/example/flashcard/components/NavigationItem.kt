package com.example.flashcard.components

import com.example.flashcard.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Categories : NavigationItem("categories", R.drawable.ic_categories, "Categories")
}