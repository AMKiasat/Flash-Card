package com.example.flashcard.components

import android.widget.Space
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashcard.R
import com.example.flashcard.ScreenRoute
import kotlin.math.roundToInt

//@Preview
//@Composable
//fun mmd() {
//    CategoryCard(painter = painterResource(id = R.drawable.start_now), title = "mmd")
//
//}
@Composable
fun CategoryCard(
    navController: NavController,
    painter: Painter,
    title: String,
    modifier: Modifier = Modifier
) {

    var context = LocalContext.current.applicationContext

    val deleteDialog = remember {
        mutableStateOf(false)
    }

    var x_offset = remember {
        Animatable(1f)
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
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Card(
            modifier = modifier
                .padding(15.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .offset(x = (x_offset.value * -100).roundToInt().dp),
            shape = MaterialTheme.shapes.medium,
            elevation = 5.dp,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(modifier = modifier.padding(8.dp)) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                }
                Text(
                    modifier = modifier.sizeIn(maxWidth = 170.dp),
                    text = title,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Box(modifier = modifier
                    .padding(15.dp)
                    .clickable {
                        deleteDialog.value = true
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "Delete Category"
                    )
                }
            }
        }
    }
    if (deleteDialog.value) {
        AlertDialog(
            onDismissRequest = { deleteDialog.value = false },
            title = { Text(text = "Delete category", color = Color.Black) },
            text = {
                Text(
                    text = "Are you sure you want to delete category $title?",
                    color = Color.Blue
                )
            },

            confirmButton = {

                TextButton(
                    onClick = {
                        deleteDialog.value = false
                        Toast.makeText(context, "category $title deleted.", Toast.LENGTH_SHORT)
                            .show()
                        /*TODO: delete category*/
                        navController.navigate(ScreenRoute.CategoryScreenRoute.route)
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
}
