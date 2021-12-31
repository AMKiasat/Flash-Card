package com.example.flashcard.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
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
fun CategoryCardListBox(
    modifier: Modifier = Modifier,
    live_category_list: LiveData<List<CategoryEntity>>,
    navController: NavController
) {

    val category_list by live_category_list.observeAsState(initial = emptyList())

    LazyColumn() {

        items(category_list.size) {
            val categoryObj = category_list.get(it)
            CategoryCard(
                modifier = modifier.clickable {

                    navController.navigate(ScreenRoute.InsideCategoryScreenRoute.route+"/${categoryObj.word}")
                },
                painter = painterResource(id = R.drawable.start_now),
                title = categoryObj.word
            )
        }
    }
}