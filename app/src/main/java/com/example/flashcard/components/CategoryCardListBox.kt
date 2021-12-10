package com.example.flashcard.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flashcard.objects.CategoryCard

@ExperimentalFoundationApi
@Composable
fun CategoryCardListBox(
    modifier: Modifier = Modifier,
    cards_list: ArrayList<CategoryCard>
) {
    LazyColumn() {

        items(cards_list.size) {
            val the_card = cards_list.get(it)
            CategoryCard(
                modifier = modifier,
                painter = painterResource(id = the_card.painter_id),
                title = the_card.name
            )
        }
    }
}