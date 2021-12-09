package com.example.flashcard.activities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.flashcard.CARDS_LIST
import com.example.flashcard.ScreenRoute
import com.example.flashcard.components.CategoryCardListBox


@ExperimentalFoundationApi
@Composable
fun CategoriesActivity(navController: NavController) {

    Scaffold(topBar = { },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(ScreenRoute.AddCategoryScreenRoute.route) }
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }, content = {
            CategoryCardListBox(cards_list = CARDS_LIST)
        })

}