package com.example.flashcardproject.Presentation.Flashcard

data class FlashcardUi(
    val id: Long,
    val word1: String,
    val word2: String,
    val isUp: Boolean = true,
    val folderId : Long
)