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
import com.example.flashcard.CATEGORY_LIST
import com.example.flashcard.ScreenRoute
import com.example.flashcard.components.CategoryCardListBox

@ExperimentalFoundationApi
@Composable
fun CategoryActivity(navController: NavController) {

    Scaffold(topBar = { },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(ScreenRoute.AddCategoryScreenRoute.route) }
            ) {
                Icon(Icons.Filled.Add, "")
            }
        },
        bottomBar = { BottomNavigationBar(navController = navController) }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            CategoryCardListBox(cards_list = CATEGORY_LIST)
        }
    }

}