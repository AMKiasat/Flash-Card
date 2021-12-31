package com.example.flashcard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.flashcard.MoreOptionItems
import com.example.flashcard.R
import com.example.flashcard.NavigationBarItems
import com.example.flashcard.ScreenRoute

@Composable
fun CategoryTopBar(navController: NavController, name: String) {

    var expanded by remember { mutableStateOf(false) }
    val items = listOf(
        MoreOptionItems.Delete,
//        MoreOptionItems.Sync
    )

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
            IconButton(onClick = { navController.navigate(ScreenRoute.CategoryScreenRoute.route) }) {
                Icon(
                    painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(onClick = {
//                expanded = true
                /*TODO: Delete Function*/
            }) {
                Icon(
                    painterResource(id = items[0].icon),
                    contentDescription = items[0].title
                )
            }
//            IconButton(onClick = {
////                expanded = true
//                /*TODO: Sync Function*/
//            }) {
//                Icon(
//                    painterResource(id = items[1].icon),
//                    contentDescription = items[1].title
//                )
//            }
        }
    )
//    DropdownMenu(
//        expanded = expanded,
//        onDismissRequest = { expanded = false }
//    ) {
//        DropdownMenuItem(onClick = {
//            /*TODO*/
//            expanded = false
//        }) {
//            Row() {
//                Icon(
//                    painterResource(id = items[0].icon),
//                    contentDescription = items[0].title
//                )
//                Spacer(modifier = Modifier.size(8.dp))
//                Text(
//                    text = items[0].title,
//                    maxLines = 1
//                )
//                Spacer(modifier = Modifier.size(8.dp))
//            }
//        }
//        DropdownMenuItem(onClick = {
//            /*TODO*/
//            expanded = false
//        }) {
//            Row() {
//                Icon(
//                    painterResource(id = items[1].icon),
//                    contentDescription = items[1].title
//                )
//                Spacer(modifier = Modifier.size(8.dp))
//                Text(
//                    text = items[1].title,
//                    maxLines = 1
//                )
//                Spacer(modifier = Modifier.size(8.dp))
//            }
//        }
//    }

}
