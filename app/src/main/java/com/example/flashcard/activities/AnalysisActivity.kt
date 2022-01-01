package com.example.flashcard.activities

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.flashcard.R
import com.example.flashcard.components.WordCardListBox
import com.example.flashcard.localDatabase.FlashCardDatabase
import com.example.flashcard.localDatabase.WordCardRepository
import com.example.flashcard.localDatabase.WordEntity
import com.jetpack.progressbar.CustomComponent


@ExperimentalFoundationApi
@Composable
fun AnalysisActivity(navController: NavController) {
    val context = LocalContext.current.applicationContext
    val wordRepo =
        WordCardRepository(FlashCardDatabase.getInstance(context = context).wordCardDao())
    val learnedWords = wordRepo.getLearnedWords()
    val learnedWordSize = learnedWords.size
    val allWordSize = wordRepo.getAll().size

    val liveLearnedWords: MutableLiveData<List<WordEntity>> by lazy {
        MutableLiveData<List<WordEntity>>()
    }

    liveLearnedWords.postValue(learnedWords)
    Log.d("LEARNEDWORDS", "AnalysisActivity:$liveLearnedWords ")
    Log.d("LEARNEDWORDS", "AnalysisActivity:$learnedWords ")


    Scaffold(topBar = { },
        bottomBar = { BottomNavigationBar(navController = navController) }) { innerPadding ->
        val painter = painterResource(id = R.drawable.ic_background_2)
        Box(modifier = Modifier.fillMaxSize()) {
            Background(painter = painter, contentDescription = "background")

        }
        Card(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 120.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var value by remember {
                    mutableStateOf(0)
                }
                CustomComponent(
                    indicatorValue = learnedWordSize,
                    maxIndicatorValue = allWordSize
                )

                Spacer(modifier = Modifier.fillMaxWidth())


                WordCardListBox(live_cards_list =liveLearnedWords, navController = navController)
//                TextField(
//                    value = value.toString(), onValueChange = {
//                        value = if (it.isNotEmpty()) {
//                            it.toInt()
//                        } else {
//                            0
//                        }
//                    },
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Number
//                    )
//                )
            }
        }
    }
}