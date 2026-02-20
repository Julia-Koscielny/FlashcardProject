package com.example.flashcardproject.Data.Flashcard

import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi
import kotlinx.coroutines.flow.Flow

interface FlashcardRepository {

    val flashcards: Flow<List<FlashcardUi>>

    suspend fun moveFlashcardToFolder(id: Long, folderId: Long): Int

    fun getFolderFlashcard(folderId: Long): Flow<List<FlashcardUi>>
    suspend fun insertFlashcard(flashcardEnt: FlashcardEnt)

    suspend fun updateFlashcard(flashcardEnt: FlashcardEnt)

    suspend fun deleteFlashcard(flashcardEnt: FlashcardEnt)

    suspend fun updateWord1(id: Long, word1: String)

    suspend fun updateWord2(id: Long, word2: String)

    suspend fun turnFlashcard(isUp: Boolean, id: Long)

}