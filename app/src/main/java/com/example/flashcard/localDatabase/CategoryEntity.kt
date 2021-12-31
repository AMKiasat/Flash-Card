package com.example.flashcard.localDatabase

import androidx.room.*
import com.example.flashcard.R

val DEFAULT_CATEGORY_CARD_PIC_ID = R.drawable.start_now

@Entity(tableName = "categories")
data class CategoryEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo val word: String,

    )


@Dao
interface CategoryEntityDAO {

    @Query("SELECT * FROM categories")
    fun getAll(): List<CategoryEntity>


    @Insert
    fun insert(item: CategoryEntity)

    @Delete
    suspend fun delete(user: CategoryEntity)

}

class CategoryCardRepository(private val wordEntityDatabaseDao: CategoryEntityDAO) {


    fun addCategory(wordItem: CategoryEntity) {
        wordEntityDatabaseDao.insert(wordItem)
    }


    fun getAll(): List<CategoryEntity> {
        return wordEntityDatabaseDao.getAll()
    }

    suspend fun deleteCategory(wordItem: CategoryEntity) {
        wordEntityDatabaseDao.delete(wordItem)
    }


}