package com.example.flashcardproject.Mappers.Flashcard

import com.example.flashcardproject.Data.Flashcard.FlashcardEnt
import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi

    fun FlashcardEnt.toUI(): FlashcardUi =
        FlashcardUi(
            id = id,
            word1 = word1,
            word2 = word2,
            folderId = folderId,
            isUp = true
        )
