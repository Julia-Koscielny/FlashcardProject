package com.example.flashcardproject.Presentation.Composables.Folder

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.flashcardproject.Presentation.Composables.Flashcard.SingularFlashcardScreen
import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi

@Composable
fun FolderScreen(
    flashcards: List<FlashcardUi>,
    onFlip: () -> Unit
) {
    if (flashcards.isEmpty()) {
        Text("No flashcards")
    } else {
        LazyColumn {
            items(
                items = flashcards,
                key = { it.id }
            ) { flashcard ->
                SingularFlashcardScreen(flashcard, onFlip)
            }
        }
    }
}