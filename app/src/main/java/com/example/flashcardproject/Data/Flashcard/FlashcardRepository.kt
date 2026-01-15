package com.example.flashcardproject.Data.Flashcard

import kotlinx.coroutines.flow.Flow

interface FlashcardRepository {

    suspend fun moveFlashcards(id: Long, folderId: Long): Int

    fun getFolderFlashcard(folderId: Long): Flow<List<FlashcardEnt>>
    suspend fun insertFlashcard(flashcardEnt: FlashcardEnt)

    suspend fun updateFlashcard(flashcardEnt: FlashcardEnt)

    suspend fun deleteFlashcard(flashcardEnt: FlashcardEnt)
}