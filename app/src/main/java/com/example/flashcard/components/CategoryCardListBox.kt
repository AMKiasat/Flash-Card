package com.example.flashcard.components

import android.view.MotionEvent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.flashcard.R
import com.example.flashcard.ScreenRoute
import com.example.flashcard.localDatabase.CategoryEntity
import kotlinx.coroutines.delay

@ExperimentalFoundationApi
@Composable
fun CategoryCardListBox(
    modifier: Modifier = Modifier,
    live_category_list: LiveData<List<CategoryEntity>>,
    navController: NavController
) {

    val category_list by live_category_list.observeAsState(initial = emptyList())

    LazyColumn() {

        items(category_list.size) {
            val categoryObj = category_list.get(it)
            CategoryCard(
                modifier = modifier.clickable {

                    navController.navigate(ScreenRoute.InsideCategoryScreenRoute.route+"/${categoryObj.word}")
                },

                painter = painterResource(id = R.drawable.start_now),
                title = categoryObj.word,
                navController = navController
            )
        }
    }
}
//
//@ExperimentalComposeUiApi
//@Composable
//fun RepeatingButton(
//    modifier: Modifier = Modifier,
//    onClick: () -> Unit,
//    enabled: Boolean = true,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    elevation: ButtonElevation? = ButtonDefaults.elevation(),
//    shape: Shape = MaterialTheme.shapes.small,
//    border: BorderStroke? = null,
//    colors: ButtonColors = ButtonDefaults.buttonColors(),
//    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
//    content: @Composable RowScope.() -> Unit
//) {
//
//    var pressed by remember { mutableStateOf(false) }
//
//    Button(
//        modifier = modifier.pointerInteropFilter {
//            pressed = when (it.action) {
//                MotionEvent.ACTION_DOWN -> true
//
//                else -> false
//            }
//
//            true
//        },
//        onClick = {},
//        enabled = enabled,
//        interactionSource = interactionSource,
//        elevation = elevation,
//        shape = shape,
//        border = border,
//        colors = colors,
//        contentPadding = contentPadding,
//        content = content
//    )
//}
//
//@Composable
//fun RepeatingButton(
//    modifier: Modifier = Modifier,
//    onClick: () -> Unit,
//    enabled: Boolean = true,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    elevation: ButtonElevation? = ButtonDefaults.elevation(),
//    shape: Shape = MaterialTheme.shapes.small,
//    border: BorderStroke? = null,
//    colors: ButtonColors = ButtonDefaults.buttonColors(),
//    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
//    maxDelayMillis: Long = 1000,
//    minDelayMillis: Long = 5,
//    delayDecayFactor: Float = .20f,
//    content: @Composable RowScope.() -> Unit
//) {
//    Button(
//        modifier = modifier.repeatingClickable(
//            interactionSource = interactionSource,
//            enabled = enabled,
//            maxDelayMillis = maxDelayMillis,
//            minDelayMillis = minDelayMillis,
//            decayFactor = delayDecayFactor
//        ) { onClick() },
//        onClick = {},
//        enabled = enabled,
//        interactionSource = interactionSource,
//        elevation = elevation,
//        shape = shape,
//        border = border,
//        colors = colors,
//        contentPadding = contentPadding,
//        content = content
//    )
//}
//
//fun Modifier.repeatingClickable(
//    interactionSource: InteractionSource,
//    enabled: Boolean,
//    maxDelayMillis: Long = 1000,
//    minDelayMillis: Long = 5,
//    delayDecayFactor: Float = .20f,
//    onClick: () -> Unit
//): Modifier = composed {
//
//    val currentClickListener by rememberUpdatedState(onClick)
//
//    pointerInput(interactionSource, enabled) {
//        forEachGesture {
//            coroutineScope {
//                awaitPointerEventScope {
//                    val down = awaitFirstDown(requireUnconsumed = false)
//                    val heldButtonJob = launch {
//                        var currentDelayMillis = maxDelayMillis
//                        while (enabled && down.pressed) {
//                            currentClickListener()
//                            delay(currentDelayMillis)
//                            val nextMillis = currentDelayMillis - (currentDelayMillis * delayDecayFactor)
//                            currentDelayMillis = nextMillis.toLong().coerceAtLeast(minDelayMillis)
//                        }
//                    }
//                    waitForUpOrCancellation()
//                    heldButtonJob.cancel()
//                }
//            }
//        }
//    }
//}