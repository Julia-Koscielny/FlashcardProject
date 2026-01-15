package com.example.flashcardproject.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flashcardproject.Data.Folder.FolderEnt
import com.example.flashcardproject.Data.Flashcard.FlashcardEnt
import com.example.flashcardproject.Data.Flashcard.FlashcardDAO
import com.example.flashcardproject.Data.Folder.FolderDAO


@Database(entities = [FlashcardEnt::class, FolderEnt::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun flashcardDAO(): FlashcardDAO
    abstract fun folderDAO(): FolderDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}