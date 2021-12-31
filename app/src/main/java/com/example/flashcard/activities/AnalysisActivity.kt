package com.example.flashcard.activities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.jetpack.progressbar.CustomComponent


@ExperimentalFoundationApi
@Composable
fun AnalysisActivity(navController: NavController) {
    Scaffold(topBar = { },
        bottomBar = { BottomNavigationBar(navController = navController) }) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var value by remember {
                mutableStateOf(0)
            }
            CustomComponent(
                indicatorValue = 23
            )
            TextField(
                value = value.toString(), onValueChange = {
                    value = if (it.isNotEmpty()) {
                        it.toInt()
                    } else {
                        0
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
        }
    }
}