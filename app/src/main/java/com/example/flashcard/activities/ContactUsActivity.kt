package com.example.flashcard.activities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashcard.HomeMoreOptionItems
import com.example.flashcard.components.HomeTopBar
import com.example.flashcard.components.TopBar

@ExperimentalFoundationApi
@Composable
fun ContactUsActivity(navController: NavController) {
    Scaffold(topBar = { TopBar(navController,"Contact us") },

        bottomBar = {  }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Developers email address:")
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "Kiasatamirm@gmail.com")
            Text(text = "aroozkhosh4@gmail.com")
            Text(text = "hoseinlooki0@gmail.com")
        }
    }
}