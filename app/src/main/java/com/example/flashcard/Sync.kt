package com.example.flashcard

import CategoryApi
import CategoryApiViewModel
import android.content.Context
import android.util.Log
import com.example.flashcard.api.WordApi
import com.example.flashcard.api.WordApiViewModel
import com.example.flashcard.localDatabase.*
import kotlinx.coroutines.delay
import java.util.*
import kotlin.concurrent.schedule


fun syncStore(context: Context) {
    val wordRep = WordCardRepository(FlashCardDatabase.getInstance(context = context).wordCardDao())
    val categoryRep =
        CategoryCardRepository(FlashCardDatabase.getInstance(context = context).categoryCardDao())
    val wordApi = WordApiViewModel()
    val categoryApi = CategoryApiViewModel()


    val wordEntityList = wordRep.getAll()
    val wordApiList = mutableListOf<WordApi>()


    val categoryEntityList = categoryRep.getAll()
    val categoryApiList = mutableListOf<CategoryApi>()





    wordEntityList.forEach {
        wordApiList += WordApi(
            id = null,
            pic_location = it.pic_location,
            learned = it.learned,
            rememberCount = it.rememberCount,
            rememberType = it.rememberType,
            category = it.category,
            definition = it.definition,
            lastRememberTime = it.lastRememberTime,
            word = it.word
        )
    }

    categoryEntityList.forEach {
        categoryApiList += CategoryApi(
            id = null,
            word = it.word
        )
    }

    wordApi.storeWordList(wordApiList)
    categoryApi.storeCategoryList(categoryApiList)


}


fun syncLoad(context: Context) {
    val wordRep = WordCardRepository(FlashCardDatabase.getInstance(context = context).wordCardDao())
    val categoryRep =
        CategoryCardRepository(FlashCardDatabase.getInstance(context = context).categoryCardDao())
    val wordApi = WordApiViewModel()
    val categoryApi = CategoryApiViewModel()

    wordApi.getWordList()
    categoryApi.getCategoryList()


    val wordEntityList = mutableListOf<WordEntity>()



    val categoryEntityList = mutableListOf<CategoryEntity>()




    Timer("SettingUp", false).schedule(2000) {
        val wordApiList = wordApi.returned
        val categoryApiList = categoryApi.returned
        Log.d("XXXXX", "syncLoad: $wordApiList")
        wordApiList.forEach {

            wordEntityList += WordEntity(
                id = null,
                pic_location = it.pic_location,
                learned = it.learned,
                rememberCount = it.rememberCount,
                rememberType = it.rememberType,
                category = it.category,
                definition = it.definition,
                lastRememberTime = it.lastRememberTime,
                word = it.word
            )
        }

        categoryApiList.forEach {
            categoryEntityList += CategoryEntity(
                id = null,
                word = it.word
            )
        }

        wordEntityList.forEach() {
            wordRep.addWord(it)
        }

        categoryEntityList.forEach() {
            categoryRep.addCategory(it)
        }
    }




}