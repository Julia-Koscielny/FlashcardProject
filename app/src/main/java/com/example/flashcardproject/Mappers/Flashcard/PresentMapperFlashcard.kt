package com.example.flashcardproject.Mappers.Flashcard

import com.example.flashcardproject.Domain.Flashcard
import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi

fun FlashcardUi.toFlashcard() : Flashcard {
    return Flashcard(
        id = id,
        word1 = word1,
        word2 = word2,
        isUp = isUp,
        folderId = folderId
    )
}