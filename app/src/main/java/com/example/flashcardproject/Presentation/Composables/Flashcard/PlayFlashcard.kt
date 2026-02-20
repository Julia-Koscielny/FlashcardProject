package com.example.flashcardproject.Presentation.Composables.Flashcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi
import com.example.flashcardproject.Presentation.Flashcard.FlashcardViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun FlashcardPlayScreen(
    flashcards: List<FlashcardUi>,
    viewModel: FlashcardViewModel,
    onFinished:()-> Unit,
    onCancel: ()-> Unit,
) {
    var currentIndex by remember { mutableStateOf(0) }

    if (flashcards.isEmpty()) {
        Text("No flashcards")
        return
    }

    val currentFlashcard = flashcards[currentIndex]
    val isLast = currentIndex == flashcards.lastIndex

    Column {
        SingularFlashcardScreen(
            viewModel = viewModel,
            flashcard = currentFlashcard
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (isLast) onFinished()
                else currentIndex++
            }
        ) {
            Text(if (isLast) "Finish" else "Next")
        }

        Button(onClick = onCancel) {
            Text("Cancel")
        }
    }
}
