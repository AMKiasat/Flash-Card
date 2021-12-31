package com.example.flashcard.activities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.flashcard.HomeMoreOptionItems
import com.example.flashcard.components.HomeTopBar
import com.example.flashcard.components.TopBar

@ExperimentalFoundationApi
@Composable
fun ContactUsActivity(navController: NavController) {
    Scaffold(topBar = { TopBar(navController,"Contact us") },

        bottomBar = {  }) { innerPadding ->
        Column(

        ) {

        }
    }

}