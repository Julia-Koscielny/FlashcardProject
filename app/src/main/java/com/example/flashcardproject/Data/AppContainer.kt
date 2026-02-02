package com.example.flashcardproject.Data

import android.content.Context
import com.example.flashcardproject.Data.Flashcard.FlashcardRepository
import com.example.flashcardproject.Data.Flashcard.OfflineFlashcardsRepository

interface AppContainer {
    val flashcardRepository : FlashcardRepository

}

class AppDataContainer (private val context: Context): AppContainer {
    override val flashcardRepository: FlashcardRepository by lazy {
        OfflineFlashcardsRepository(
            AppDatabase.getDatabase(context).flashcardDAO()
        )
    }

}