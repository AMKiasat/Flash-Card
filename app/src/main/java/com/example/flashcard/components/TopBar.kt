package com.example.flashcard.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flashcard.HomeMoreOptionItems
import com.example.flashcard.MoreOptionItems
import com.example.flashcard.R
import com.example.flashcard.ScreenRoute

@Composable
fun TopBar(navController: NavController, name: String) {

    TopAppBar(
        title = {
            Text(
                text = name,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(ScreenRoute.HomeScreenRoute.route) }) {
                Icon(
                    painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back"
                )
            }
        }
    )
}