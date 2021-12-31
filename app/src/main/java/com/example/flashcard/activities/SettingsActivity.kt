package com.example.flashcard.activities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.flashcard.components.HomeTopBar
import com.example.flashcard.components.TopBar

@ExperimentalFoundationApi
@Composable
fun SettingsActivity(navController: NavController) {
    Scaffold(topBar = { TopBar(navController, "Settings") },

        bottomBar = {  }) { innerPadding ->

    }

}