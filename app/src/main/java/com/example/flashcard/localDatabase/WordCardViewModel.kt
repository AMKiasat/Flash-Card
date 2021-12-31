package com.example.flashcard.localDatabase

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordCardRepository
    val relatedToCategoryWord: MutableLiveData<List<WordCard>> by lazy {
        MutableLiveData<List<WordCard>>(listOf())
    }

    val allWord: MutableLiveData<List<WordCard>> by lazy {
        MutableLiveData<List<WordCard>>(listOf())
    }

    val notifWords: MutableLiveData<List<WordCard>> by lazy {
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

    fun getRelatedWords(category_name: String): LiveData<List<WordCard>> {
        viewModelScope.launch(Dispatchers.IO) {

            relatedToCategoryWord.postValue(repository.getRelatedWordsWithCategory(category_name))
            Log.d("CATEGORY ITEMS", "get_related_words: ${relatedToCategoryWord}")
        }
        return relatedToCategoryWord
    }


    fun getWordsToNotify(): LiveData<List<WordCard>> {

        viewModelScope.launch(Dispatchers.IO) {

            notifWords.postValue(repository.getWordsToNotify())
            Log.d("NOTIF WORDS", "get_words_to_notify: ${notifWords}")
        }
        return notifWords
    }

    fun getAll(): LiveData<List<WordCard>> {

        viewModelScope.launch(Dispatchers.IO) {
            allWord.postValue(repository.getAll())
        }
        return allWord
    }

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