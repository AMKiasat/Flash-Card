package com.example.flashcard.activities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@ExperimentalFoundationApi
@Composable
fun AnalysisActivity(navController: NavController) {
    Scaffold(topBar = { },
        bottomBar = { BottomNavigationBar(navController = navController) }) { innerPadding ->

    }
}