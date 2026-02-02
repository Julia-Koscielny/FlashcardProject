package com.example.flashcardproject.Data.Flashcard

import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.flashcardproject.Mappers.Flashcard.toUI

class OfflineFlashcardsRepository(private val flashcardDAO: FlashcardDAO) : FlashcardRepository {

    override val flashcards: Flow<List<FlashcardUi>> =
        flashcardDAO.getFlashcards()
            .map { list ->
            list.map { it.toUI()}
        }

    override suspend fun moveFlashcardToFolder(id: Long, folderId: Long) = flashcardDAO.moveFlashcardToFolder(id,folderId)

    override fun getFolderFlashcard(folderId: Long): Flow<List<FlashcardEnt>> = flashcardDAO.getFolderFlashcard(folderId)

    override suspend fun insertFlashcard(flashcardEnt: FlashcardEnt) = flashcardDAO.insertFlashcard(flashcardEnt)

    override suspend fun updateFlashcard(flashcardEnt: FlashcardEnt) = flashcardDAO.updateFlashcard(flashcardEnt)

    override suspend fun deleteFlashcard(flashcardEnt: FlashcardEnt) = flashcardDAO.deleteFlashcard(flashcardEnt)

    override suspend fun updateWord1(id: Long, word1: String) = flashcardDAO.updateWord1(id,word1)

    override suspend fun updateWord2(id: Long, word2: String) = flashcardDAO.updateWord2(id, word2)

    override suspend fun turnFlashcard(isUp: Boolean, id: Long) = flashcardDAO.turnFLashcard(isUp,id)
}