package com.example.flashcard.components

import androidx.compose.animation.core.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import com.example.flashcard.R
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun WordCard(
    painter: Painter,
    title: String,
    modifier: Modifier = Modifier
) {
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
                contentDescription = title,
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
                        text = title,
                        style = TextStyle(color = Color.White, fontSize = 16.sp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Box(modifier = modifier
                        .padding(1.dp)
                        .clickable { /*TODO: Delete Word function*/ }) {
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