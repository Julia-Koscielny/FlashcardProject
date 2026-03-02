package com.example.flashcardproject.Presentation.Composables.Flashcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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

    Column(modifier = Modifier.fillMaxSize() .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        SingularFlashcardScreen(
            viewModel = viewModel,
            flashcard = currentFlashcard,
            modifier = Modifier.heightIn(min = 400.dp) .fillMaxWidth(0.9f) .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (isLast) onFinished()
                else currentIndex++
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(if (isLast) "Finish" else "Next")
        }

        Button(
            onClick = onCancel,
            modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Cancel")
        }
    }
}
