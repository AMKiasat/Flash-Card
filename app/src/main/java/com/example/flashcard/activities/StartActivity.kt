package com.example.flashcard.activities

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.flashcard.R
import com.example.flashcard.ScreenRoute
import kotlinx.coroutines.delay


@Composable
fun StartActivity(navController: NavController) {
    var scale = remember {
        Animatable(0f)

    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 3000,
                easing = LinearEasing

            )
        )
        delay(3000)
        navController.navigate(ScreenRoute.CategoryScreenRoute.route)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()

    ) {

        Image(
            painter = painterResource(id = R.drawable.start_now),
            contentDescription = "asd",
            modifier = Modifier.scale(scale.value)
        )
    }


}