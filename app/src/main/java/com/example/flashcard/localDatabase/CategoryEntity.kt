package com.example.flashcard.localDatabase

import android.database.sqlite.SQLiteConstraintException
import androidx.room.*
import com.example.flashcard.R

val DEFAULT_CATEGORY_CARD_PIC_ID = R.drawable.start_now

@Entity(
    tableName = "categories", indices = [
        Index(value = ["word"], unique = true)
    ]
)
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

    @Query("SELECT * FROM categories WHERE word LIKE (:searchStr)")
    suspend fun searchWord(searchStr: String): List<CategoryEntity>

}

class CategoryCardRepository(private val categoryEntityDatabaseDao: CategoryEntityDAO) {


    fun addCategory(wordItem: CategoryEntity) {
        try {

            categoryEntityDatabaseDao.insert(wordItem)
        } catch (e: SQLiteConstraintException) {

        }
    }


    suspend fun search(searchStr: String): List<CategoryEntity> {
        return categoryEntityDatabaseDao.searchWord(searchStr = searchStr)
    }

    fun getAll(): List<CategoryEntity> {
        return categoryEntityDatabaseDao.getAll()
    }

    suspend fun deleteCategory(wordItem: CategoryEntity) {
        categoryEntityDatabaseDao.delete(wordItem)
    }


}