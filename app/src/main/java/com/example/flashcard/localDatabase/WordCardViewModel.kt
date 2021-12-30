package com.example.flashcard.localDatabase

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WordViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: List<WordCard>
    private val repository: WordCardRepository

    init {
        val wordCardDao = FlashCardDatabase.getInstance(application).wordCardDao()
        repository = WordCardRepository(wordCardDao)
        this.readAllData = repository.readAllData
    }

    fun addWord(todoItem: WordCard) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWord(todoItem)
        }
    }

    fun getAll(): List<WordCard> {

        viewModelScope.launch(Dispatchers.IO) {
            repository.readAllData
        }
        return repository.readAllData
    }

    fun updateWord(todoItem: WordCard) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWord(todoItem = todoItem)
        }
    }

    fun deleteWord(todoItem: WordCard) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWord(todoItem = todoItem)
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