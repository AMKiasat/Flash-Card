package com.example.flashcard.api

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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

class Temp(
    @SerializedName("kir")
    @Expose
    var docor: List<WordApi>
)


interface ApiService {

    @GET("word")
    suspend fun getWords(): List<WordApi>

    @POST("word")
    suspend fun setWords(@Body wordList: List<WordApi>): List<WordApi>

    companion object {
        var apiService: ApiService? = null
        fun getInstance(): ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://flash-card-flash-card.fandogh.cloud/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }

}

class WordApiViewModel : ViewModel() {
    var errorMessage: String by mutableStateOf("")
    var returned =mutableListOf<WordApi>()

    fun getWordList() {
        val apiService = ApiService.getInstance()
        viewModelScope.launch {
            try {
                returned = apiService.getWords() as MutableList<WordApi>

                Log.d("XXXXX", "onCreate: $returned")

            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("wordApiListERROR", "getWordList:$errorMessage ")
            }
        }
    }




    fun storeWordList(wordList: List<WordApi>) {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                Log.d("wordApiList", "sending $wordList")
                apiService.setWords(wordList = wordList)
                Log.d("wordApiList", "send succses")

            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("wordApiListERROR", "$errorMessage ")
            }
        }

    }

}