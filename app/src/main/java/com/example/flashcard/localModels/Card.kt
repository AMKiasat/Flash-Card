package com.example.flashcard.localModels

import androidx.room.*


@Entity
data class WordCard(

    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val pic_location: String?,
    @ColumnInfo val word: String?,
    @ColumnInfo val definition: String?,
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
    fun insertAll(vararg users: WordCard)

    @Delete
    fun delete(user: WordCard)
}