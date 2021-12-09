package com.example.flashcard.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.flashcard.cards


@Composable
fun ImageCardList(
    modifier : Modifier=Modifier,
    painter :Painter
){

    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        if (cards.size % 2 == 0) {
            var j = 0
            for (i in 1..(cards.size / 2)) {
                Row() {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(16.dp)
                    ) {
                        ImageCard(
                            painter = painter,
                            title = cards.get(j).word
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        ImageCard(
                            painter = painter,
                            title = cards.get(j + 1).word
                        )
                    }
                }
                j += 2
            }
        } else {
            var j = 0
            for (i in 1..(cards.size / 2)) {
                Row() {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(16.dp)
                    ) {
                        ImageCard(
                            painter = painter,
                            title = cards.get(j).word
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        ImageCard(
                            painter = painter,
                            title = cards.get(j + 1).word
                        )
                    }
                }
                j += 2
            }
            Row() {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(16.dp)
                ) {
                    ImageCard(
                        painter = painter,
                        title = cards.get(cards.size - 1).word
                    )
                }
            }
        }
    }
}