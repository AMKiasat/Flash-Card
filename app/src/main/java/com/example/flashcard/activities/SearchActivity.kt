package com.example.flashcard.activities

import android.app.Application
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.flashcard.components.*
import com.example.flashcard.localDatabase.CategoryEntityViewModel
import com.example.flashcard.localDatabase.WordEntity
import com.example.flashcard.localDatabase.WordEntityViewModel

@ExperimentalFoundationApi
@Composable
fun SearchActivity(navController: NavController) {

    val catViewModel =
        CategoryEntityViewModel(LocalContext.current.applicationContext as Application)
    val wordViewModel = WordEntityViewModel(LocalContext.current.applicationContext as Application)


    var searchText by remember {
        mutableStateOf("%%")
    }

    val cards_list by wordViewModel.search(searchText).observeAsState(initial = emptyList())

    Scaffold(topBar = { },

        bottomBar = { BottomNavigationBar(navController = navController) }) { innerPadding ->
        val painter = painterResource(id = R.drawable.ic_background_6)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Background(painter = painter, contentDescription = "background")

            Column() {
                Box(modifier = Modifier.padding(innerPadding)) {
                    SearchBar(
                        hint = "Search...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        searchText = "%" + it + "%"
                        Log.d("searchText", "SearchActivity: $searchText")
//                        val wordSearchRes = wordViewModel.search(it)
                    }
                }
                Card(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 5.dp,
                    backgroundColor = Color.White.copy(alpha = 0.8f)
                ) {

                    LazyColumn() {
                        stickyHeader {
                            Text(text = "Categories Found:", modifier = Modifier.padding(8.dp))
                        }

                        item {
                            CategorySearch(
                                live_category_list = catViewModel.search(searchText),
                                navController = navController
                            )
                        }
                        stickyHeader {
                            Text(text = "Words Found:", modifier = Modifier.padding(8.dp))
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
//                        Grid{
//                            WordSearch(
//                                live_cards_list = wordViewModel.search(searchText),
//                                navController = navController
//                            )
//                        }
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

fun LazyListScope.gridItems(
    count: Int,
    nColumns: Int,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    itemContent: @Composable BoxScope.(Int) -> Unit,
) {
    gridItems(
        data = List(count) { it },
        nColumns = nColumns,
        horizontalArrangement = horizontalArrangement,
        itemContent = itemContent,
    )
}

fun <T> LazyListScope.gridItems(
    data: List<T>,
    nColumns: Int,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    itemContent: @Composable BoxScope.(T) -> Unit,
) {
    val rows = if (data.count() == 0) 0 else 1 + (data.count() - 1) / nColumns
    items(rows) { rowIndex ->
        Row(horizontalArrangement = horizontalArrangement) {
            for (columnIndex in 0 until nColumns) {
                val itemIndex = rowIndex * nColumns + columnIndex
                if (itemIndex < data.count()) {
                    Box(
                        modifier = Modifier.weight(1f, fill = true),
                        propagateMinConstraints = true
                    ) {
                        itemContent.invoke(this, data[itemIndex])
                    }
                } else {
                    Spacer(Modifier.weight(1f, fill = true))
                }
            }
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