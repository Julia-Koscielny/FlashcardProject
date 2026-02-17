package com.example.flashcardproject

import android.app.Application
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.example.flashcardproject.Data.AppDatabase
import com.example.flashcardproject.Data.Flashcard.OfflineFlashcardsRepository

/*
class FlashcardApp: Application() {
    val database by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration()
            .build()

    }
}*/

class FlashcardApp: Application(){

    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}