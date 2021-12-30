package com.example.flashcard.localDatabase

import androidx.room.*
import com.example.flashcard.R

val DEFAULT_WORD_CARD_PIC_ID = R.drawable.start_now

@Entity
data class WordCard(

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
interface WordCardDao {
    @Query("SELECT * FROM wordcard")
    fun getAll(): List<WordCard>

//    @Query("SELECT * FROM wordcard WHERE id IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<WordCard>


    @Query("SELECT * FROM wordcard WHERE category=(:category_name)")
    fun get_related_words_with_category(category_name: String): List<WordCard>

//    example
//    @Query("SELECT * FROM wordcard WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): WordCard


    @Insert
    suspend fun insert(item: WordCard)

    @Delete
    suspend fun delete(user: WordCard)

    @Update
    suspend fun update(item: WordCard)


    @Query("DELETE FROM wordcard")
    suspend fun deleteAllTodos()
}

class WordCardRepository(private val wordCardDatabaseDao: WordCardDao) {

//    val readAllData: List<WordCard> = wordCardDatabaseDao.getAll()

    suspend fun addWord(wordItem: WordCard) {
        wordCardDatabaseDao.insert(wordItem)
    }

    fun get_related_words_with_category(category: String): List<WordCard> {
        return wordCardDatabaseDao.get_related_words_with_category(category_name = category)
    }

    suspend fun updateWord(wordItem: WordCard) {
        wordCardDatabaseDao.update(wordItem)
    }

    suspend fun deleteWord(wordItem: WordCard) {
        wordCardDatabaseDao.delete(wordItem)
    }

    suspend fun deleteAllWords() {
        wordCardDatabaseDao.deleteAllTodos()
    }
}