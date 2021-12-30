package com.example.flashcard.localDatabase

import androidx.room.*
import com.example.flashcard.R

val DEFAULT_CATEGORY_CARD_PIC_ID = R.drawable.start_now

@Entity
data class CategoryCard(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo val word: String,

    )


@Dao
interface CategoryCardDAO {

    @Query("SELECT * FROM categorycard")
    fun getAll(): List<CategoryCard>


    @Insert
    suspend fun insert(item: CategoryCard)

    @Delete
    suspend fun delete(user: CategoryCard)

}

class CategoryCardRepository(private val wordCardDatabaseDao: CategoryCardDAO) {


    suspend fun addCategory(wordItem: CategoryCard) {
        wordCardDatabaseDao.insert(wordItem)
    }


    fun getAll(): List<CategoryCard> {
        return wordCardDatabaseDao.getAll()
    }

    suspend fun deleteCategory(wordItem: CategoryCard) {
        wordCardDatabaseDao.delete(wordItem)
    }


}