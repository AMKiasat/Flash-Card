package com.example.flashcard.localDatabase

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CategoryEntityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CategoryCardRepository
    val returnedVal: MutableLiveData<List<CategoryEntity>> by lazy {
        MutableLiveData<List<CategoryEntity>>(listOf())
    }

    val returnedSearch: MutableLiveData<List<CategoryEntity>> by lazy {
        MutableLiveData<List<CategoryEntity>>(listOf())
    }





    init {
        val categoryCardDao = FlashCardDatabase.getInstance(application).categoryCardDao()
        repository = CategoryCardRepository(categoryCardDao)
    }



    fun addCategory(todoItem: CategoryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCategory(todoItem)
        }
    }

    fun search(searchStr:String): LiveData<List<CategoryEntity>> {
        viewModelScope.launch(Dispatchers.IO) {
            returnedSearch.postValue(repository.search(searchStr))
            Log.d("CATEGORIS", "get_all: ${returnedSearch}")
        }
        return returnedSearch
    }


    fun getAll(): LiveData<List<CategoryEntity>> {
        viewModelScope.launch(Dispatchers.IO) {

            returnedVal.postValue(repository.getAll())
            Log.d("CATEGORIS", "get_all: ${returnedVal}")
        }
        return returnedVal
    }


    fun deleteCategory(todoItem: CategoryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCategory(wordItem = todoItem)
        }
    }


}
