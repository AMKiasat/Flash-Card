package com.example.flashcard.activities

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashcard.R
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
    val painter = painterResource(id = R.drawable.ic_background_6)
    Box(modifier = Modifier.fillMaxSize()) {
        Background(painter = painter, contentDescription = "background")

    }
    Card(
        modifier = Modifier
            .padding(horizontal = 30.dp,vertical = 250.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
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
}
