package com.example.flashcard.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flashcard.*
import com.example.flashcard.R

@Composable
fun HomeTopBar(navController: NavController) {

    var expanded by remember { mutableStateOf(false) }
    val context =LocalContext.current.applicationContext
    var title by remember {
        mutableStateOf("Flashcards")
    }

    TopAppBar(
        title = {
            Text(
                text = title,
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

        }, actions = {
            IconButton(onClick = {
                syncStore(context = context)

            }) {
                Icon(
                    painterResource(id = MoreOptionItems.Upload.icon),
                    contentDescription = MoreOptionItems.Upload.title
                )
            }


            IconButton(onClick = {
                syncLoad(context = context)

            }) {
                Icon(
                    painterResource(id = MoreOptionItems.Download.icon),
                    contentDescription = MoreOptionItems.Download.title
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
                    painterResource(id = HomeMoreOptionItems.Settings.icon),
                    contentDescription = HomeMoreOptionItems.Settings.title
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = HomeMoreOptionItems.Settings.title,
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
                    painterResource(id = HomeMoreOptionItems.ContactUs.icon),
                    contentDescription = HomeMoreOptionItems.ContactUs.title
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = HomeMoreOptionItems.ContactUs.title,
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}