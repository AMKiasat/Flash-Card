package com.example.flashcard.localDatabase

import androidx.room.*
import com.example.flashcard.R

val DEFAULT_WORD_CARD_PIC_ID = R.drawable.start_now

@Entity(tableName = "words")
data class WordEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo val pic_location: String?,
    @ColumnInfo val word: String,
    @ColumnInfo val definition: String,
    @ColumnInfo(defaultValue = "all") val category: String?,
    @ColumnInfo val remembertime: String,
)


@Dao
interface WordEntityDao {


    @Query("SELECT * FROM words")
    fun getAll(): List<WordEntity>

//    @Query("SELECT * FROM wordcard WHERE id IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<WordCard>


    @Query("SELECT * FROM words WHERE category=(:category_name)")
    fun get_related_words_with_category(category_name: String): List<WordEntity>

    @Query("SELECT * FROM words WHERE remembertime < TIME() ")
    fun get_words_to_notify(): List<WordEntity>


    @Insert
    suspend fun insert(item: WordEntity)

    @Delete
    suspend fun delete(user: WordEntity)

    @Update
    suspend fun update(item: WordEntity)


    @Query("DELETE FROM words")
    suspend fun deleteAllTodos()
}

class WordCardRepository(private val wordEntityDatabaseDao: WordEntityDao) {


    suspend fun addWord(wordItem: WordEntity) {
        wordEntityDatabaseDao.insert(wordItem)
    }

    fun getRelatedWordsWithCategory(category: String): List<WordEntity> {
        return wordEntityDatabaseDao.get_related_words_with_category(category_name = category)
    }

    fun getAll(): List<WordEntity> {
        return wordEntityDatabaseDao.getAll()
    }

    fun getWordsToNotify(): List<WordEntity> {
        return wordEntityDatabaseDao.get_words_to_notify()
    }

    suspend fun updateWord(wordItem: WordEntity) {
        wordEntityDatabaseDao.update(wordItem)
    }

    suspend fun deleteWord(wordItem: WordEntity) {
        wordEntityDatabaseDao.delete(wordItem)
    }

    suspend fun deleteAllWords() {
        wordEntityDatabaseDao.deleteAllTodos()
    }
}