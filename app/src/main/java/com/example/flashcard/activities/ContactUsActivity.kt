package com.example.flashcard.activities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashcard.HomeMoreOptionItems
import com.example.flashcard.R
import com.example.flashcard.components.HomeTopBar
import com.example.flashcard.components.TopBar

@ExperimentalFoundationApi
@Composable
fun ContactUsActivity(navController: NavController) {
    Scaffold(topBar = { TopBar(navController, "Contact us") },
        bottomBar = { }) { innerPadding ->
        val painter = painterResource(id = R.drawable.ic_background_6)
        Box(modifier = Modifier.fillMaxSize()) {
            Background(painter = painter, contentDescription = "background")

        }
        Card(
            modifier = Modifier
                .padding(horizontal = 90.dp, vertical = 230.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp,
            backgroundColor = Color.White.copy(alpha = 0.8f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
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
}
