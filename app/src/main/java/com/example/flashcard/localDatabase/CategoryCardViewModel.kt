package com.example.flashcard.localDatabase

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CategoryCardViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CategoryCardRepository
    val returnedVal: MutableLiveData<List<CategoryCard>> by lazy {
        MutableLiveData<List<CategoryCard>>(listOf())
    }

    init {
        val categoryCardDao = FlashCardDatabase.getInstance(application).categoryCardDao()
        repository = CategoryCardRepository(categoryCardDao)
    }

    fun addCategory(todoItem: CategoryCard) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCategory(todoItem)
        }
    }

    fun getAll(): LiveData<List<CategoryCard>> {
        viewModelScope.launch(Dispatchers.IO) {

            returnedVal.postValue(repository.getAll())
            Log.d("CATEGORIS", "get_all: ${returnedVal}")
        }
        return returnedVal
    }


    fun deleteCategory(todoItem: CategoryCard) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCategory(wordItem = todoItem)
        }
    }


}
