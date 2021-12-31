package com.example.flashcard.localDatabase

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordCardRepository
    val relatedToCategoryWord: MutableLiveData<List<WordEntity>> by lazy {
        MutableLiveData<List<WordEntity>>(listOf())
    }

    val allWord: MutableLiveData<List<WordEntity>> by lazy {
        MutableLiveData<List<WordEntity>>(listOf())
    }

    val notifWords: MutableLiveData<List<WordEntity>> by lazy {
        MutableLiveData<List<WordEntity>>(listOf())
    }

    init {
        val wordEntityDao = FlashCardDatabase.getInstance(application).wordCardDao()
        repository = WordCardRepository(wordEntityDao)
    }

    fun addWord(todoItem: WordEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWord(todoItem)
        }
    }

    fun getRelatedWords(category_name: String): LiveData<List<WordEntity>> {
        viewModelScope.launch(Dispatchers.IO) {

            relatedToCategoryWord.postValue(repository.getRelatedWordsWithCategory(category_name))
            Log.d("CATEGORY ITEMS", "get_related_words: ${relatedToCategoryWord}")
        }
        return relatedToCategoryWord
    }


    fun getWordsToNotify(): LiveData<List<WordEntity>> {

        viewModelScope.launch(Dispatchers.IO) {

            notifWords.postValue(repository.getWordsToNotify())
            Log.d("NOTIF WORDS", "get_words_to_notify: ${notifWords}")
        }
        return notifWords
    }

    fun getAll(): LiveData<List<WordEntity>> {

        viewModelScope.launch(Dispatchers.IO) {
            allWord.postValue(repository.getAll())
        }
        return allWord
    }

    fun updateWord(todoItem: WordEntity) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWord(wordItem = todoItem)
        }
    }

    fun deleteWord(todoItem: WordEntity) {
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