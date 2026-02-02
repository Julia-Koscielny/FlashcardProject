package com.example.flashcardproject

import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi
import com.example.flashcardproject.Presentation.Folder.FolderUi


data class FlashCardsAppUiState(
    val flashcards: List<FlashcardUi> = emptyList(),
    val folders: List<FolderUi> = emptyList(),
    val selectedFolderId: Long? = null) {
}