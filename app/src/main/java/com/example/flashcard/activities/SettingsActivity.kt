package com.example.flashcard.activities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.flashcard.R
import com.example.flashcard.components.HomeTopBar
import com.example.flashcard.components.TopBar

@ExperimentalFoundationApi
@Composable
fun SettingsActivity(navController: NavController) {
    Scaffold(topBar = { TopBar(navController, "Settings") },

        bottomBar = {  }) { innerPadding ->
        val painter = painterResource(id = R.drawable.ic_background_2)
        Box(modifier = Modifier.fillMaxSize()) {
            Background(painter = painter, contentDescription = "background")

        }
    }

}