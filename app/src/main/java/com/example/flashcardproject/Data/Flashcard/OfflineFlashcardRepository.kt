package com.example.flashcardproject.Data.Flashcard

import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val flashcardDAO: FlashcardDAO) : FlashcardRepository {
    override suspend fun moveFlashcards(id: Long, folderId: Long) = flashcardDAO.moveFlashcards(id,folderId)

    override fun getFolderFlashcard(folderId: Long): Flow<List<FlashcardEnt>> = flashcardDAO.getFolderFlashcard(folderId)

    override suspend fun insertFlashcard(flashcardEnt: FlashcardEnt) = flashcardDAO.insertFlashcard(flashcardEnt)

    override suspend fun updateFlashcard(flashcardEnt: FlashcardEnt) = flashcardDAO.updateFlashcard(flashcardEnt)

    override suspend fun deleteFlashcard(flashcardEnt: FlashcardEnt) = flashcardDAO.deleteFlashcard(flashcardEnt)
}