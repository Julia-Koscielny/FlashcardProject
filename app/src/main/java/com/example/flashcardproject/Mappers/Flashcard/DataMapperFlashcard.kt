package com.example.flashcardproject.Mappers.Flashcard

import com.example.flashcardproject.Data.Flashcard.FlashcardEnt
import com.example.flashcardproject.Domain.Flashcard

fun FlashcardEnt.toFlashcard(): Flashcard {
    return Flashcard(
        id = id,
        word1=word1,
        word2 = word2,
        folderId=folderId,
        isUp=true,
        next_date=0,
        price_value=0,
        creation_date=0
    )
}