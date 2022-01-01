package com.example.flashcard.activities

import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.flashcard.R
import com.example.flashcard.ScreenRoute
import com.example.flashcard.components.CategoryCardListBox
import com.example.flashcard.localDatabase.CategoryEntityViewModel

@ExperimentalFoundationApi
@Composable
fun CategoryActivity(navController: NavController) {
    val viewModel = CategoryEntityViewModel(LocalContext.current.applicationContext as Application)
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
        val painter = painterResource(id = R.drawable.ic_background_2)
        Box(modifier = Modifier.fillMaxSize()) {
            Background(painter = painter, contentDescription = "background")

            Box(modifier = Modifier.padding(innerPadding)) {
                CategoryCardListBox(
                    live_category_list = categoryList,
                    navController = navController
                )
            }
        }
    }

}