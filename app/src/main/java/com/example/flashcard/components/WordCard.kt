package com.example.flashcard.components

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flashcard.R
import com.example.flashcard.ScreenRoute
import com.example.flashcard.localDatabase.WordEntity
import com.example.flashcard.localDatabase.WordEntityViewModel
import kotlin.math.roundToInt

@Composable
fun WordCard(
    painter: Painter,
    modifier: Modifier = Modifier,
    wordEntity: WordEntity,
    navController: NavController
) {
    var context = LocalContext.current.applicationContext
    val wordEntityViewModel = WordEntityViewModel(context as Application)

    val deleteDialog = remember {
        mutableStateOf(false)
    }

    val learningDialog = remember {
        mutableStateOf(false)
    }

    var baseOffset by remember {
        mutableStateOf(0)
    }
    modifier.offset(baseOffset.dp)


    var x_offset = remember {
        Animatable(1f)
    }

    var show = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true) {
        x_offset.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 500,
                easing = LinearEasing

            )
        )
    }


    Card(
        modifier = modifier
            .fillMaxWidth()
            .offset(x = (x_offset.value * -100).roundToInt().dp)
            .clickable { learningDialog.value = true },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = painter,
                contentDescription = wordEntity.word,
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )

                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    Text(
                        modifier = modifier.sizeIn(maxWidth = 120.dp),
                        text = wordEntity.word,
                        style = TextStyle(color = Color.White, fontSize = 16.sp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        color = Color(255,165,0)
                    )
                    Box(modifier = modifier
                        .padding(1.dp)
                        .clickable {
                            deleteDialog.value = true
//                            wordEntityViewModel.deleteWord(wordEntity)
//                            navController.navigate(ScreenRoute.InsideCategoryScreenRoute.route + "/${wordEntity.category}")
//                            clickState.value +=1
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete_white),
                            contentDescription = "Delete Word",
                            tint = Color(255,165,0)
                        )
                    }
                }
            }
        }

    }
    if (deleteDialog.value) {
        AlertDialog(
            onDismissRequest = { deleteDialog.value = false },
            title = { Text(text = "Delete word", color = Color.Black) },
            text = {
                Text(text = "Are you sure you want to delete word " + wordEntity.word + "?", color = Color.Blue)
            },

            confirmButton = {

                TextButton(
                    onClick = {
                        deleteDialog.value = false
                        Toast.makeText(context, "Word " + wordEntity.word + " deleted.", Toast.LENGTH_SHORT).show()
                        wordEntityViewModel.deleteWord(wordEntity)
                        navController.navigate(ScreenRoute.InsideCategoryScreenRoute.route + "/${wordEntity.category}")
                    }) {
                    Text(text = "Yes", color = Color.Green)
                }

            },
            dismissButton = {
                TextButton(
                    onClick = {
                        deleteDialog.value = false
                    }) {
                    Text(text = "No", color = Color.Red)
                }
            },
//            backgroundColor = Color.LightGray,
//            contentColor = Color.Blue
        )
    }

    if (learningDialog.value) {
        AlertDialog(
            onDismissRequest = { learningDialog.value = false },
            title = { Text(text = wordEntity.word + " :", color = Color.Black) },
            text = {
                Text(text = wordEntity.definition, color = Color.Blue)
            },

            confirmButton = {

                TextButton(
                    onClick = {
                        learningDialog.value = false
                        wordEntity.learned =true
                        wordEntityViewModel.updateWord(wordEntity)

                        Toast.makeText(context, wordEntity.word + " learned.", Toast.LENGTH_SHORT).show()
                    }) {
                    Text(text = "Got it !", color = Color.Green)
                }

            },
            dismissButton = {
                TextButton(
                    onClick = {
                        learningDialog.value = false
                        wordEntity.learned =false
                        wordEntityViewModel.updateWord(wordEntity)
                    }) {
                    Text(text = "Wrong :(", color = Color.Red)
                }
            },
//            backgroundColor = Color.LightGray,
//            contentColor = Color.Blue
        )
    }
}


fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}

@Composable
fun alertDialog(
    alertmsg: String
) {

    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Card's Backside", color = Color.Black) },
            text = {
                Text(text = alertmsg, color = Color.Blue)
            },

            confirmButton = {

                TextButton(
                    onClick = {
                        openDialog.value = false
                        Toast.makeText(context, "WAY TO GO !", Toast.LENGTH_SHORT).show()
                    }) {
                    Text(text = "Got it !", color = Color.Green)
                }

            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        Toast.makeText(context, "It's okay ", Toast.LENGTH_SHORT).show()
                    }) {
                    Text(text = "Wrong :(", color = Color.Red)
                }
            },
            backgroundColor = Color.LightGray,
            contentColor = Color.Blue
        )
    }
}