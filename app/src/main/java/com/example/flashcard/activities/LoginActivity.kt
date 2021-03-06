package com.example.flashcard.activities

import AppPref
import LoginApiViewModel
import LoginData
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashcard.R
import com.example.flashcard.ScreenRoute
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoginActivity(navController: NavController) {

    val apiModel = LoginApiViewModel()

    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    var message by remember {
        mutableStateOf("")
    }


    val x_offset = remember {
        Animatable(1f)
    }

    val appContext = LocalContext.current

    val painter = painterResource(id = R.drawable.ic_background_1)


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
        TextField(value = username,
            modifier = usernameModifier,
            onValueChange = {
                username = it
            },
            singleLine = true,
            label = { Text("User Name") },
        )
        Spacer(modifier = Modifier.size(8.dp))
        val passwordModifier = Modifier.offset(x = (x_offset.value * 100).roundToInt().dp)
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = passwordModifier,
            label = { Text("Password") },
            placeholder = { Text("Password") },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisibility)
                    R.drawable.ic_eye
                else R.drawable.ic_hide

                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painterResource(id = image),
                        contentDescription = "Visibility"
                    )
                }
            }
        )


        Spacer(
            modifier = Modifier
                .size(8.dp)
                .width(20.dp)
        )

        val buttonModifier = Modifier.offset(y = (x_offset.value * 200).roundToInt().dp)
        Button(
            onClick = {
                apiModel.checkLogin(LoginData(username, password))
                val status = apiModel.status
                if (status == "200") {

                    navController.navigate(ScreenRoute.WordScreenRoute.route)

                }

                message = "wrong credential!"


            },
            modifier = buttonModifier.fillMaxWidth(0.3f)
        ) {
            Text(text = "sign in ")
        }

        Text(text = message)


    }

    val context = LocalContext.current

    val pref = AppPref(context = context)
    val scope = rememberCoroutineScope()



    scope.launch {
        loginAndSave(username = username, password = password, pref)
    }

}

suspend fun loginAndSave(username: String, password: String, pref: AppPref) {

    Log.d("LOGIN", "loginAndSave: $username $password")
    pref.putString(AppPref.USERNAME, username)
    pref.putString(AppPref.PASSWORD, password)

}




