package com.example.flashcard.activities

import android.widget.TimePicker
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashcard.CARDS_LIST
import com.example.flashcard.ScreenRoute
import com.example.flashcard.objects.WordCard


@Composable
fun AddWordActivity(navController: NavController) {
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
                Text("Enter your new word")
            }
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "How often do you want to remind this word ?")
        Spacer(modifier = Modifier.size(15.dp))
        val radioOptions = listOf("Daily", "Weekly", "Monthly")
        val (selectedOption, onOptionSelected) = remember {
            mutableStateOf(radioOptions[0])
        }
        radioOptions.forEach { text ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(horizontal = 10.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    modifier = Modifier.padding(all = Dp(value = 8F)),
                    onClick = {
                        onOptionSelected(text)
                    }
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                val card = WordCard()
                card.word = text
                card.remembertime = selectedOption
                CARDS_LIST.add(card)
                navController.navigate(ScreenRoute.WordScreenRoute.route)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Add")
        }
    }
}
