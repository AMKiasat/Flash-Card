package com.example.flashcard.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flashcard.R
import com.example.flashcard.objects.Card






@ExperimentalFoundationApi
@Composable
fun ImageCardList(
    modifier : Modifier=Modifier,
    cards_list : ArrayList<Card>
){


    LazyVerticalGrid(

        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(bottom = 10.dp)
    ) {

        items(cards_list.size) {
            val  the_card = cards_list.get(it)
            ImageCard(
                modifier = modifier.padding(10.dp),
                painter = painterResource(id = the_card.painter_id) ,
                title = the_card.word
            )
        }
    }
    
   
}

