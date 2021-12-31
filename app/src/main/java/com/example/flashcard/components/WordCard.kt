package com.example.flashcard.components

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
            .offset(x = (x_offset.value * -100).roundToInt().dp),
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
                        maxLines = 1
                    )
                    Box(modifier = modifier
                        .padding(1.dp)
                        .clickable {
                            wordEntityViewModel.deleteWord(wordEntity)
                            navController.navigate(ScreenRoute.InsideCategoryScreenRoute.route + "/${wordEntity.category}")
//                            clickState.value +=1
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete_white),
                            contentDescription = "Delete Word",
                            tint = Color.White
                        )
                    }
                }
            }
        }

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