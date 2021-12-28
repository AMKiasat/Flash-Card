package com.example.flashcard.activities

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
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
fun SplashImage(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 0.3f, animationSpec = tween(durationMillis = 500, easing = {
            OvershootInterpolator(2f).getInterpolation(it)
        }))
        delay(5000)
        navController.navigate(ScreenRoute.CategoryScreenRoute.route)
    }
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.splashimage),
            contentDescription = "Logoscreen",
            modifier = Modifier.scale(scale.value)
        )


    }
}