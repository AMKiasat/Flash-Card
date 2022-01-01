package com.example.flashcard.activities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.flashcard.R
import com.example.flashcard.ScreenRoute
import com.example.flashcard.components.HomeTopBar
import com.example.flashcard.components.WordCardListBox
import com.example.flashcard.localDatabase.WordEntity

@ExperimentalFoundationApi
@Composable
fun HomeActivity(navController: NavController) {
    Scaffold(topBar = { HomeTopBar(navController) },
        bottomBar = { BottomNavigationBar(navController = navController) }) { innerPadding ->
        val painter = painterResource(id = R.drawable.ic_background_6)
        Box(modifier = Modifier.fillMaxSize()) {
            Background(painter = painter, contentDescription = "background")

        }
    }

}

@Composable
fun Background(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier
){
    Box(modifier = Modifier.fillMaxWidth())

    Image(painter = painter, contentDescription = contentDescription ,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize())
}