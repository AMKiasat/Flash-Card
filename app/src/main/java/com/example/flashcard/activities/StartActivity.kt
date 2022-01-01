package com.example.flashcard.activities

import AppPref
import android.annotation.SuppressLint
import android.util.Log
import android.view.animation.OvershootInterpolator
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

    val context = LocalContext.current.applicationContext
    val appPref = AppPref(context = context)
    if (!isBuilt) {
        LaunchedEffect(key1 = true) {
            val haveTask = appPref.getString("taskkk")
            Log.d("TASKBUILD", "StartActivity: $haveTask")
            if (haveTask == null || haveTask.toBoolean() == false) {
                appPref.putString("taskkk", "true")
                build_task(context)
                isBuilt = true
            }
        }


    }


    val scale = remember {
        Animatable(0f)

    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 0.3f, animationSpec = tween(durationMillis = 500, easing = {
            OvershootInterpolator(2f).getInterpolation(it)
        }))
        delay(500)
        navController.navigate(ScreenRoute.CategoryScreenRoute.route)
    }
    val painter = painterResource(id = R.drawable.splashimage)
    Box(modifier = Modifier.fillMaxSize()) {
        Background(painter = painter, contentDescription = "background")

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
        delay(1000)

        val status = checkLogin(pref)
        if (status) {
            navController.navigate(ScreenRoute.HomeScreenRoute.route)
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


