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
import androidx.navigation.NavController
import com.example.flashcard.CARDS_LIST
import com.example.flashcard.SELECTED_CATEGORY
import com.example.flashcard.ScreenRoute
import com.example.flashcard.components.WordCardListBox
import com.example.flashcard.objects.CategoryCard


@ExperimentalFoundationApi
@Composable
fun InsideCategoryActivity(navController: NavController) {

    Scaffold(topBar = { },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(ScreenRoute.AddWordScreenRoute.route) }
            ) {
                Icon(Icons.Filled.Add, "")
            }
        },
        bottomBar = { BottomNavigationBar(navController = navController) }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            WordCardListBox(cards_list = SELECTED_CATEGORY.cards_list)
        }
    }

}