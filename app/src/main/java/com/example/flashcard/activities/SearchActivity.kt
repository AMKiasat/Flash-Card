package com.example.flashcard.activities

import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.flashcard.R
import com.example.flashcard.components.CategoryCardListBox
import com.example.flashcard.components.WordCardListBox
import com.example.flashcard.localDatabase.CategoryEntityViewModel
import com.example.flashcard.localDatabase.WordEntity

@ExperimentalFoundationApi
@Composable
fun SearchActivity(navController: NavController) {

    val viewModel = CategoryEntityViewModel(LocalContext.current.applicationContext as Application)
    val categoryList = viewModel.getAll()
    val returnedVal: MutableLiveData<List<WordEntity>> by lazy {
        MutableLiveData<List<WordEntity>>(listOf())
    }

    Scaffold(topBar = { },

        bottomBar = { BottomNavigationBar(navController = navController) }) { innerPadding ->
        val painter = painterResource(id = R.drawable.ic_background_6)
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Background(painter = painter, contentDescription = "background")

            Column() {
                Box(modifier = Modifier.padding(innerPadding)) {
                    SearchBar(
                        hint = "Search...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {

                    }
                }
                Card(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 5.dp,
                    backgroundColor = Color.White.copy(alpha = 0.8f)
                ) {
                    Column() {

                        Row() {
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text(text = "Categories Found:")
                        }
                        CategoryCardListBox(
                            live_category_list = categoryList,
                            navController = navController
                        ) /*TODO: use the functions commented instead*/
                        Spacer(modifier = Modifier.padding(8.dp))
                        Row() {
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text(text = "Words Found:")
                        }
                        WordCardListBox(
                            live_cards_list = returnedVal,
                            navController = navController
                        )
                    }
                }
            }

        }
    }

}


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}
//
//fun searchCategoryList(query: String) {
//
//    var cachedCategoryList = listOf<CategoryEntity>()
//    var isSearchStarting = true
//    var isSearching = mutableStateOf(false)
//
//    val listToSearch = cachedCategoryList
//    viewModelScope.launch(Dispatchers.Default) {
//        if (query.isEmpty()) {
//            pokemonList.value = cachedPokemonList
//            isSearching.value = false
//            isSearchStarting = true
//            return@launch
//        }
//        val results = listToSearch.filter {
//            it.pokemonName.contains(query.trim(), ignoreCase = true) ||
//                    it.number.toString() == query.trim()
//        }
//        if (isSearchStarting) {
//            cachedPokemonList = pokemonList.value
//            isSearchStarting = false
//        }
//        pokemonList.value = results
//        isSearching.value = true
//    }
//}
//
//fun searchWordList(query: String) {
//
//    var cachedWordList = listOf<WordEntity>()
//    var isSearchStarting = true
//    var isSearching = mutableStateOf(false)
//
//    val listToSearch = cachedCategoryList
//    viewModelScope.launch(Dispatchers.Default) {
//        if (query.isEmpty()) {
//            pokemonList.value = cachedPokemonList
//            isSearching.value = false
//            isSearchStarting = true
//            return@launch
//        }
//        val results = listToSearch.filter {
//            it.pokemonName.contains(query.trim(), ignoreCase = true) ||
//                    it.number.toString() == query.trim()
//        }
//        if (isSearchStarting) {
//            cachedPokemonList = pokemonList.value
//            isSearchStarting = false
//        }
//        pokemonList.value = results
//        isSearching.value = true
//    }
//}