package com.example.flashcard.activities

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashcard.ScreenRoute
import com.example.flashcard.localDatabase.WordEntity
import com.example.flashcard.localDatabase.WordViewModel


@Composable
fun AddWordActivity(navController: NavController, category_name: String? = "all") {
    val viewModel = WordViewModel(LocalContext.current.applicationContext as Application)

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


        val options = ArrayList<String>()
//        if (CATEGORY_LIST.size > 0) {
//            for (i in CATEGORY_LIST)
//                options.add(i.name)
//            var expanded by remember { mutableStateOf(false) }
//            var selectedText by remember { mutableStateOf("") }
//            var textFieldSize by remember { mutableStateOf(Size.Zero) }
//
//            val icon = if (expanded)
//                Icons.Filled.KeyboardArrowUp
//            else
//                Icons.Filled.KeyboardArrowDown
//
//
//            OutlinedTextField(
//                value = selectedText,
//                onValueChange = {
//                    selectedText = it
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .onGloballyPositioned { coordinates ->
//                        //This value is used to assign to the DropDown the same width
//                        textFieldSize = coordinates.size.toSize()
//                    },
//                label = { Text("Add to category") },
//                trailingIcon = {
//                    Icon(icon, "contentDescription",
//                        Modifier.clickable { expanded = !expanded })
//                }
//            )
//            DropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false },
//                modifier = Modifier
//                    .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
//            ) {
//                options.forEach { label ->
//                    DropdownMenuItem(onClick = {
//                        selectedText = label
//                        expanded = false
//                    }) {
//                        Text(text = label)
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.height(8.dp))
//        }


        Button(
            onClick = {
                if (text == "") {
                    Toast.makeText(context, "Enter some words", Toast.LENGTH_SHORT).show()
                } else {
                    val card = WordEntity(
                        word = text, remembertime = selectedOption,
                        category = category_name, definition = "asd", id = null, pic_location = null
                    )

                    viewModel.addWord(card)
                    navController.navigate(ScreenRoute.InsideCategoryScreenRoute.route + "/$category_name")
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Add")
        }
    }
}
