package com.example.flashcard

import CategoryApi
import CategoryApiViewModel
import android.content.Context
import com.example.flashcard.api.WordApi
import com.example.flashcard.api.WordApiViewModel
import com.example.flashcard.localDatabase.CategoryCardRepository
import com.example.flashcard.localDatabase.FlashCardDatabase
import com.example.flashcard.localDatabase.WordCardRepository


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
            id = it.id,
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
            id = it.id,
            word = it.word
        )
    }

    wordApi.storeWordList(wordApiList)
    categoryApi.storeCategoryList(categoryApiList)


}