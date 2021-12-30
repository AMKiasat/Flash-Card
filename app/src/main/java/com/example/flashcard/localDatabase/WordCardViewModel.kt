package com.example.flashcard.localDatabase

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordCardRepository
    val returnedVal: MutableLiveData<List<WordCard>> by lazy {
        MutableLiveData<List<WordCard>>(listOf())
    }

    init {
        val wordCardDao = FlashCardDatabase.getInstance(application).wordCardDao()
        repository = WordCardRepository(wordCardDao)
    }

    fun addWord(todoItem: WordCard) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWord(todoItem)
        }
    }

    fun get_related_words(category_name: String): LiveData<List<WordCard>> {
        viewModelScope.launch(Dispatchers.IO) {

            returnedVal.postValue(repository.get_related_words_with_category(category_name))
            Log.d("CATEGORY ITEMS", "get_related_words: ${returnedVal}")
        }
        return returnedVal
    }

//    fun getAll(): List<WordCard> {
//
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.readAllData
//        }
//        return repository.readAllData
//    }

    fun updateWord(todoItem: WordCard) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWord(wordItem = todoItem)
        }
    }

    fun deleteWord(todoItem: WordCard) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWord(wordItem = todoItem)
        }
    }

    fun deleteAllWords() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllWords()
        }
    }
}

class TodoViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            return WordViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}