package com.example.flashcard.activities

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashcard.ScreenRoute
import com.example.flashcard.localDatabase.CategoryEntity
import com.example.flashcard.localDatabase.CategoryEntityViewModel

@Composable
fun AddCategoryActivity(navController: NavController) {
    val viewModel = CategoryEntityViewModel(LocalContext.current.applicationContext as Application)

    val context = LocalContext.current

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
            maxLines = 1,
            singleLine = true,
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
                if (text == "") {
                    Toast.makeText(context, "Enter some words", Toast.LENGTH_SHORT).show()
                } else {
                    val categoryCard = CategoryEntity(word = text, id = null)
                    viewModel.addCategory(categoryCard)
                    navController.navigate(ScreenRoute.CategoryScreenRoute.route)
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Add")
        }
    }
}
