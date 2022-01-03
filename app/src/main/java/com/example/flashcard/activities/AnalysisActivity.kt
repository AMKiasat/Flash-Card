package com.example.flashcard.activities

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.flashcard.R
import com.example.flashcard.components.WordCard
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

    val cards_list by liveLearnedWords.observeAsState(initial = emptyList())

    liveLearnedWords.postValue(learnedWords)
    Log.d("LEARNEDWORDS", "AnalysisActivity:$liveLearnedWords ")
    Log.d("LEARNEDWORDS", "AnalysisActivity:$learnedWords ")


    Scaffold(topBar = { },
        bottomBar = { BottomNavigationBar(navController = navController) }) { innerPadding ->
        val painter = painterResource(id = R.drawable.ic_background_6)
        Box(modifier = Modifier.fillMaxSize()) {
            Background(painter = painter, contentDescription = "background")

        }
        Card(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 80.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp,
            backgroundColor = Color.White.copy(alpha = 0.8f)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    var value by remember {
                        mutableStateOf(0)
                    }
                    CustomComponent(
                        indicatorValue = learnedWordSize,
                        maxIndicatorValue = allWordSize
                    )
                }
                stickyHeader {
                    Text(text = "Learned words:")
                }
                gridItems(cards_list.size, nColumns = 2) { index ->
                    val the_card = cards_list.get(index)
                    WordCard(
                        modifier = Modifier.padding(10.dp),
                        painter = painterResource(id = R.drawable.start_now),
                        wordEntity = the_card,
                        navController = navController
                    )
                }
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