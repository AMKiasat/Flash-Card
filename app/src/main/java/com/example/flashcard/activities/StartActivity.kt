package com.example.flashcard.activities

import AppPref
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.flashcard.R
import com.example.flashcard.ScreenRoute
import com.example.flashcard.build_task
import kotlinx.coroutines.delay


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun StartActivity(navController: NavController) {
    var isBuilt by remember { mutableStateOf(false) }

    if (!isBuilt) {
        build_task(LocalContext.current.applicationContext)
        isBuilt = true
    }


    val scale = remember {
        Animatable(0f)

    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()

    ) {

        Image(
            painter = painterResource(id = R.drawable.splashimage),
            contentDescription = "asd",
            modifier = Modifier.scale(scale.value)
        )
    }

    val pref = AppPref(context = LocalContext.current)

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = LinearEasing

            )
        )
        delay(500)

        val status = checkLogin(pref)
        if (status) {
            navController.navigate(ScreenRoute.WordScreenRoute.route)
        } else {
            navController.navigate(ScreenRoute.LoginScreenRoute.route)
        }
    }


}

suspend fun checkLogin(appPref: AppPref): Boolean {

    val username = appPref.getString(AppPref.USERNAME)
    Log.d("YOYOYOY", "checkLogin: ${username}")
    return username != null


}


