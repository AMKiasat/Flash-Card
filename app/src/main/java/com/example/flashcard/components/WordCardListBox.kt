package com.example.flashcard.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flashcard.objects.Card


@ExperimentalFoundationApi
@Composable
fun WordCardListBox(
    modifier: Modifier = Modifier,
    cards_list: ArrayList<Card>
) {


    LazyVerticalGrid(

        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(bottom = 10.dp),
    ) {

        items(cards_list.size) {
            val the_card = cards_list.get(it)
            WordCard(
                modifier = modifier.padding(10.dp),
                painter = painterResource(id = the_card.painter_id),
                title = the_card.word
            )
        }
    }


}

