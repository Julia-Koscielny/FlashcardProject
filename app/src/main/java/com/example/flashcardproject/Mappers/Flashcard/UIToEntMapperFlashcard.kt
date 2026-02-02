package com.example.flashcardproject.Mappers.Flashcard

import com.example.flashcardproject.Data.Flashcard.FlashcardEnt
import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi

fun FlashcardUi.toEntity(): FlashcardEnt =
    FlashcardEnt(
         id = id,
         folderId = folderId,
         word2 = word2,
         word1 = word1,
         isUp = true,
         next_date = 0,
         price_value = 0,
         creation_date = 0
    )
