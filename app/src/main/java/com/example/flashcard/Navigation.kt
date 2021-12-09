package com.example.flashcard

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flashcard.Components.ImageCard

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.AddWordScreen.route) {
            AddWordScreen(navController = navController)
        }
    }
}

val cards = ArrayList<Card>()

@Composable
fun MainScreen(navController: NavController) {

    val scrollState = rememberScrollState()
    val painter = painterResource(id = R.drawable.start_now)
    val add_pic = painterResource(id = R.drawable.add)
//            words.add("start3")
//            words.add("start4")
//            words.add("start5")
    Scaffold(topBar = { },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddWordScreen.route) }
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }, content = {
            Column(
                modifier = Modifier.verticalScroll(scrollState)
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
        })

}

@Composable
fun AddWordScreen(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }
    val often by remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Enter your new word")
            }
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "How often do you want to remind this word ?")
        Spacer(modifier = Modifier.size(8.dp))
//        Column {
//            RadioButton(selected = often == , onClick = {
//
//            })
//        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                val card = Card()
                card.word = text
                cards.add(card)
                navController.navigate(Screen.MainScreen.route)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Add")
        }
    }
}



//object Often {
//    const val daily = "0"
//    const val weekly = "1"
//    const val monthly = "2"
//}