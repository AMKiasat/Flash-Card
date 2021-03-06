package com.example.flashcard.localDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WordEntity::class , CategoryEntity::class], version = 1, exportSchema = false)
abstract class FlashCardDatabase : RoomDatabase() {
    abstract fun wordCardDao(): WordEntityDao
    abstract fun categoryCardDao(): CategoryEntityDAO

    companion object {

        private var INSTANCE: FlashCardDatabase? = null

        fun getInstance(context: Context): FlashCardDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FlashCardDatabase::class.java,
                        "flash_card_database"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}

