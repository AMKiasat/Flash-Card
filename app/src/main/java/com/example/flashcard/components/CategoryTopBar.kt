package com.example.flashcard.components

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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

    var context = LocalContext.current.applicationContext

    val deleteDialog = remember {
        mutableStateOf(false)
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    val items = listOf(
        MoreOptionItems.Delete,
//        MoreOptionItems.Sync
    )

    TopAppBar(
        backgroundColor = Color(255,165,0),
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
                deleteDialog.value = true
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
    if (deleteDialog.value) {
        AlertDialog(
            onDismissRequest = { deleteDialog.value = false },
            title = { Text(text = "Delete category", color = Color.Black) },
            text = {
                Text(
                    text = "Are you sure you want to delete category $name?",
                    color = Color.Blue
                )
            },

            confirmButton = {

                TextButton(
                    onClick = {
                        deleteDialog.value = false
                        Toast.makeText(context, "category $name deleted.", Toast.LENGTH_SHORT).show()
                        /*TODO: delete category*/
                        navController.navigate(ScreenRoute.CategoryScreenRoute.route)
                    }) {
                    Text(text = "Yes", color = Color.Green)
                }

            },
            dismissButton = {
                TextButton(
                    onClick = {
                        deleteDialog.value = false
                    }) {
                    Text(text = "No", color = Color.Red)
                }
            },
//            backgroundColor = Color.LightGray,
//            contentColor = Color.Blue
        )
    }
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
