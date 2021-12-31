package com.example.flashcard.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.flashcard.R
import com.example.flashcard.localDatabase.WordEntity

@ExperimentalFoundationApi
@Composable
fun WordCardListBox(
    modifier: Modifier = Modifier,
    live_cards_list: LiveData<List<WordEntity>>,
    navController: NavController
) {

    val cards_list by live_cards_list.observeAsState(initial = emptyList())


    LazyVerticalGrid(

        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(bottom = 10.dp),
    ) {

        items(cards_list.size) {
            val the_card = cards_list.get(it)
            WordCard(
                modifier = modifier.padding(10.dp),
                painter = painterResource(id = R.drawable.start_now),
                wordEntity = the_card,
                navController=navController
            )
        }
    }
}