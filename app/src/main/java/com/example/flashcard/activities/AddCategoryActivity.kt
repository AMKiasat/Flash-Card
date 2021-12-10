package com.example.flashcard.activities

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashcard.CARDS_LIST
import com.example.flashcard.CATEGORY_LIST
import com.example.flashcard.ScreenRoute
import com.example.flashcard.objects.CategoryCard
import com.example.flashcard.objects.WordCard

@Composable
fun AddCategoryActivity(navController: NavController) {
    var text by remember {
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
                Text("Enter your category name")
            }
        )
        Spacer(modifier = Modifier.size(8.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                val categoryCard = CategoryCard()
                categoryCard.name = text
                CATEGORY_LIST.add(categoryCard)
                navController.navigate(ScreenRoute.CategoryScreenRoute.route)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Add")
        }
    }
}
