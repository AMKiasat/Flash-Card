package com.example.flashcard.localDatabase

import android.database.sqlite.SQLiteConstraintException
import androidx.room.*
import com.example.flashcard.R

val DEFAULT_WORD_CARD_PIC_ID = R.drawable.start_now
val HOURLY_REMEMBER_TYPE = "hourly"
val DAILY_REMEMBER_TYPE = "daily"
val WEEKLY_REMEMBER_TYPE = "weekly"


@Entity(
    tableName = "words", indices = [
        Index(value = ["word", "category"], unique = true)
    ]
)
data class WordEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo val pic_location: String?,
    @ColumnInfo val word: String,
    @ColumnInfo val definition: String,
    @ColumnInfo(defaultValue = "all") val category: String,
    @ColumnInfo val lastRememberTime: String,
    @ColumnInfo val rememberType: String,
    @ColumnInfo val rememberCount: Int,
    @ColumnInfo var learned: Boolean,


    )


@Dao
interface WordEntityDao {


    @Query("SELECT * FROM words")
    fun getAll(): List<WordEntity>

//    @Query("SELECT * FROM wordcard WHERE id IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<WordCard>


    @Query("SELECT * FROM words WHERE category=(:category_name)")
    fun get_related_words_with_category(category_name: String): List<WordEntity>

    @Query("SELECT * FROM words WHERE lastRememberTime < TIME('now') ")
    fun get_words_to_notify(): List<WordEntity>

    @Query("SELECT * FROM words WHERE learned=1")
    fun get_learned_words(): List<WordEntity>


    @Insert
    fun insert(item: WordEntity)

    @Delete
    suspend fun delete(user: WordEntity)

    @Update
    suspend fun update(item: WordEntity)


    @Query("DELETE FROM words")
    suspend fun deleteAllTodos()
}

class WordCardRepository(private val wordEntityDatabaseDao: WordEntityDao) {


    fun addWord(wordItem: WordEntity) {
        try {

            wordEntityDatabaseDao.insert(wordItem)
        }catch (e: SQLiteConstraintException){

        }
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

    fun getLearnedWords(): List<WordEntity> {
        return wordEntityDatabaseDao.get_learned_words()
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