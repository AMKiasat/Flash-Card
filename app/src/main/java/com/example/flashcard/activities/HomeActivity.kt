package com.example.flashcard.activities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.flashcard.ScreenRoute
import com.example.flashcard.components.HomeTopBar
import com.example.flashcard.components.WordCardListBox
import com.example.flashcard.localDatabase.WordEntity

@ExperimentalFoundationApi
@Composable
fun HomeActivity(navController: NavController) {
    Scaffold(topBar = { HomeTopBar(navController) },

        bottomBar = { BottomNavigationBar(navController = navController) }) { innerPadding ->
    }

}