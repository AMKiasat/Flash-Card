package com.example.flashcard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashcard.components.ImageCardList
import com.example.flashcard.objects.Card

@ExperimentalFoundationApi
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

@ExperimentalFoundationApi
@Composable
fun MainScreen(navController: NavController) {

    val painter = painterResource(id = R.drawable.start_now)
//    val add_pic = painterResource(id = R.drawable.add)
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
            ImageCardList(cards_list = cards)
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
