package com.example.flashcard.activities

import android.app.Application
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashcard.R
import com.example.flashcard.ScreenRoute
import com.example.flashcard.helpers.formatTime
import com.example.flashcard.localDatabase.*
import java.time.LocalDateTime
import kotlin.math.roundToInt


@Composable
fun AddWordActivity(navController: NavController, category_name: String? = "all") {
    val viewModel = WordEntityViewModel(LocalContext.current.applicationContext as Application)

    val context = LocalContext.current

    var text by remember {
        mutableStateOf("")
    }

    var definition by remember {
        mutableStateOf("")
    }

    val painter = painterResource(id = R.drawable.ic_background_6)
    Box(modifier = Modifier.fillMaxSize()) {
        Background(painter = painter, contentDescription = "background")

    }
    Card(
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 130.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
        backgroundColor = Color.White.copy(alpha = 0.8f),

    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            TextField(
                value = text,
                maxLines = 1,
                singleLine = true,
                onValueChange = {
                    text = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White),
                label = {
                    Text("Word")
                }
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "How often do you want to remind this word ?")
            Spacer(modifier = Modifier.size(15.dp))
            val radioOptions =
                listOf(DAILY_REMEMBER_TYPE, WEEKLY_REMEMBER_TYPE, HOURLY_REMEMBER_TYPE)
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


//        val options = ArrayList<String>()
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
            TextField(
                value = definition,
                maxLines = 2,
                onValueChange = {
                    definition = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White),
                label = {
                    Text("Definition")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (text == "") {
                        Toast.makeText(context, "Enter a word", Toast.LENGTH_SHORT).show()
                    }
                    else if (definition == "") {
                        Toast.makeText(context, "Enter a definition", Toast.LENGTH_SHORT).show()
                    } else {
                        val card = WordEntity(
                            word = text,
                            lastRememberTime = formatTime(LocalDateTime.now()),
                            rememberType = selectedOption,
                            category = category_name,
                            definition = definition,
                            id = null,
                            pic_location = null,
                            rememberCount = 0,
                            learned = false

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
}

@Composable
fun PickImageFromGallery() {

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->
                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(400.dp)
                        .padding(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = { launcher.launch("image/*") }) {
            Text(text = "Pick Image")
        }
    }

}
