package com.example.flashcard.localDatabase

import androidx.lifecycle.LiveData
import androidx.room.*


@Entity
data class WordCard(

    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val pic_location: String? ,
    @ColumnInfo val word: String,
    @ColumnInfo val definition: String,
)


@Dao
interface WordCardDao {
    @Query("SELECT * FROM wordcard")
    fun getAll(): List<WordCard>

    @Query("SELECT * FROM wordcard WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<WordCard>

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

    val readAllData: List<WordCard> = wordCardDatabaseDao.getAll()

    suspend fun addWord(todoItem: WordCard) {
        wordCardDatabaseDao.insert(todoItem)
    }

    suspend fun updateWord(todoItem: WordCard) {
        wordCardDatabaseDao.update(todoItem)
    }

    suspend fun deleteWord(todoItem: WordCard) {
        wordCardDatabaseDao.delete(todoItem)
    }

    suspend fun deleteAllWords() {
        wordCardDatabaseDao.deleteAllTodos()
    }
}