package com.example.flashcard.api

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


data class WordApi(
    val id: Int?,
    val pic_location: String?,
    val word: String,
    val definition: String,
    val category: String?,
    val lastRememberTime: String,
    val rememberType: String,
    val rememberCount: Int,
    val learned: Boolean,
)


interface ApiService {

    @GET("word")
    suspend fun getMovies() : List<WordApi>

    @POST("word")
    suspend fun setWords(@Body wordList: List<WordApi>):String

    companion object {
        var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://flash-card-flash-card.fandogh.cloud/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }

}

class WordApiViewModel : ViewModel() {
    var movieListResponse:List<WordApi> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getWordList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val movieList = apiService.getMovies()
                movieListResponse = movieList
                Log.d("wordApiList", "onCreate: $movieList")
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("wordApiListERROR", "getWordList:$errorMessage ")
            }
        }
    }

    fun storeWordList(wordList: List<WordApi>){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                Log.d("wordApiList", "sending $wordList")
                apiService.setWords(wordList = wordList)
                Log.d("wordApiList", "send succses")

            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("wordApiListERROR", "$errorMessage ")
            }
        }

    }

}