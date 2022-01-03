package com.example.flashcard.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.flashcard.R
import com.example.flashcard.ScreenRoute
import com.example.flashcard.localDatabase.CategoryEntity

@ExperimentalFoundationApi
@Composable
fun CategorySearch(
    modifier: Modifier = Modifier,
    live_category_list: LiveData<List<CategoryEntity>>,
    navController: NavController
) {

    val category_list by live_category_list.observeAsState(initial = emptyList())

    Column() {
        for (item in category_list) {
            CategoryCard(
                modifier = modifier.clickable {

                    navController.navigate(ScreenRoute.InsideCategoryScreenRoute.route+"/${item.word}")
                },

                painter = painterResource(id = R.drawable.start_now),
                categoryEntity = item,
                navController = navController
            )
        }
    }
}