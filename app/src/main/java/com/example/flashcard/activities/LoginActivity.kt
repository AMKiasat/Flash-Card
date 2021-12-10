package com.example.flashcard.activities

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashcard.ScreenRoute
import kotlin.math.roundToInt

@Composable
fun LoginActivity(navController: NavController) {

    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }


    val x_offset = remember {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {

        val usernameModifier = Modifier.offset(x = (x_offset.value * -100).roundToInt().dp)
        Text(text = "username", modifier = usernameModifier)
        TextField(value = username,modifier = usernameModifier, onValueChange = {
            username = it
        }, singleLine = true)

        Spacer(modifier = Modifier.size(8.dp))

        val passwordModifier = Modifier.offset(x = (x_offset.value * 100).roundToInt().dp)
        Text(text = "password", modifier = passwordModifier)
        TextField(value = password, modifier = passwordModifier, onValueChange = {
            password = it
        }, singleLine = true)


        Spacer(
            modifier = Modifier
                .size(8.dp)
                .width(20.dp)
        )

        val buttonModifier = Modifier.offset(y = (x_offset.value * 200).roundToInt().dp)
        Button(
            onClick = {
                navController.navigate(ScreenRoute.CategoryScreenRoute.route)
            },
            modifier = buttonModifier.fillMaxWidth(0.3f)
        ) {
            Text(text = "sign in ")
        }


    }

}




