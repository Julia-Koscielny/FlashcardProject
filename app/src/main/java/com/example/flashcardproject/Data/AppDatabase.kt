package com.example.flashcardproject.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flashcardproject.Data.Folder.FolderEnt
import com.example.flashcardproject.Data.Flashcard.FlashcardEnt
import com.example.flashcardproject.Data.Flashcard.FlashcardDAO
import com.example.flashcardproject.Data.Folder.FolderDAO
import com.example.flashcardproject.Data.StoreObject.StoreObjectDAO
import com.example.flashcardproject.Data.StoreObject.StoreObjectEnt
import com.example.flashcardproject.Data.User.UserDAO
import com.example.flashcardproject.Data.User.UserEnt


@Database(entities = [FlashcardEnt::class, FolderEnt::class, UserEnt::class, StoreObjectEnt::class], version = 3)
abstract class AppDatabase: RoomDatabase() {

    abstract fun flashcardDAO(): FlashcardDAO
    abstract fun folderDAO(): FolderDAO

    abstract fun userDAO(): UserDAO

    abstract fun storeObjectDAO(): StoreObjectDAO

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