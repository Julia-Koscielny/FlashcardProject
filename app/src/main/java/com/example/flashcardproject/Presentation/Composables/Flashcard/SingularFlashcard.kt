package com.example.flashcardproject.Presentation.Composables.Flashcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi
import com.example.flashcardproject.Presentation.Flashcard.FlashcardViewModel

// SINGULAR FLASHCARD
@Composable
fun SingularFlashcardScreen(
    flashcard: FlashcardUi,
    viewModel: FlashcardViewModel) {
    SingularFlashcardContent(
        word = if (flashcard.isUp) flashcard.word1 else flashcard.word2,
        onFlip = {
            viewModel.turnFlashcard(
                isUp = !flashcard.isUp,
                id = flashcard.id
            )
        }
    )
}


@Composable
fun SingularFlashcardContent(
    word: String,
    onFlip: () -> Unit
) {

    Column(modifier = Modifier.
    padding(16.dp)) {
        Card(
            modifier = Modifier
                .padding(16.dp),
            onClick = onFlip
        ) {
            Text(
                text = word,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


//PREVIEWS


@Preview(showBackground = true)
@Composable
fun  SingularFlashcardScreenPreview(){
    SingularFlashcardContent(
        word = "Hello",
        onFlip = {}
    )
}


