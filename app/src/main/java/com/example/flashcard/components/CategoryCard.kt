package com.example.flashcard.components

import android.widget.Space
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashcard.R
import kotlin.math.roundToInt

//@Preview
//@Composable
//fun mmd() {
//    CategoryCard(painter = painterResource(id = R.drawable.start_now), title = "mmd")
//
//}
@Composable
fun CategoryCard(
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
                text = title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}
