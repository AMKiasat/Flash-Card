package com.example.flashcard.activities

import android.app.Application
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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.flashcard.ScreenRoute
import com.example.flashcard.components.CategoryCardListBox
import com.example.flashcard.localDatabase.CategoryCardViewModel

@ExperimentalFoundationApi
@Composable
fun CategoryActivity(navController: NavController) {
    val viewModel = CategoryCardViewModel(LocalContext.current.applicationContext as Application)
    val categoryList = viewModel.getAll()
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
            CategoryCardListBox(live_category_list = categoryList, navController = navController)
        }
    }

}