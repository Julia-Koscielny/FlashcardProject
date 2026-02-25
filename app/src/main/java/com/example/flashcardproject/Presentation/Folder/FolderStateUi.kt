package com.example.flashcardproject.Presentation.Folder

import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi

data class FolderStateUi (
    val folders: List<FolderUi> = emptyList(),
    val selectedFolderId: Long? = null,
    val flashcards: List<FlashcardUi> = emptyList()
)