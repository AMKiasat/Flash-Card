package com.example.flashcard.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.flashcard.SELECTED_CATEGORY
import com.example.flashcard.ScreenRoute
import com.example.flashcard.objects.CategoryCard

@ExperimentalFoundationApi
@Composable
fun CategoryCardListBox(
    modifier: Modifier = Modifier,
    category_list: ArrayList<CategoryCard>,
    navController: NavController
) {
    LazyColumn() {

        items(category_list.size) {
            val the_card = category_list.get(it)
            CategoryCard(
                modifier = modifier.clickable {
                    SELECTED_CATEGORY = the_card
                    navController.navigate(ScreenRoute.InsideCategoryScreenRoute.route)
                },
                painter = painterResource(id = the_card.painter_id),
                title = the_card.name
            )
        }
    }
}