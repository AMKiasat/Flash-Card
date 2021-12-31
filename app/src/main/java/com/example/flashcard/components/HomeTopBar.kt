package com.example.flashcard.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flashcard.HomeMoreOptionItems
import com.example.flashcard.MoreOptionItems
import com.example.flashcard.R
import com.example.flashcard.ScreenRoute

@Composable
fun HomeTopBar(navController: NavController) {

    var expanded by remember { mutableStateOf(false) }
    val items = listOf(
        HomeMoreOptionItems.Settings,
        HomeMoreOptionItems.ContactUs
    )
    TopAppBar(
        title = {
            Text(
                text = "Flashcards +",
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu"
                )
            }
        }
    )
    DropdownMenu(
        modifier = Modifier.sizeIn(minWidth = 150.dp),
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(onClick = {
            navController.navigate(ScreenRoute.SettingsScreenRoute.route)
            expanded = false
        }) {
            Row() {
                Icon(
                    painterResource(id = items[0].icon),
                    contentDescription = items[0].title
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = items[0].title,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
        DropdownMenuItem(onClick = {
            navController.navigate(ScreenRoute.ContactUsScreenRoute.route)
            expanded = false
        }) {
            Row() {
                Icon(
                    painterResource(id = items[1].icon),
                    contentDescription = items[1].title
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = items[1].title,
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}