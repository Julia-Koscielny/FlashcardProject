package com.example.flashcardproject.Presentation.Composables.Flashcard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi
import com.example.flashcardproject.Presentation.Flashcard.FlashcardViewModel

@Composable
fun CreateFlashcardScreen(
    viewModel: FlashcardViewModel,
    onCreateClick: () -> Unit,
    folderId: Long
) {
    CreateFlashcardContent (
        onCreateFlashcard = { word1, word2 ->
            viewModel.insertFlashcard(
                FlashcardUi(
                    id = 0,
                    word1 = word1,
                    word2 = word2,
                    folderId = folderId,
                    isUp = true
                )
            )
            onCreateClick()
        }
    )
}

@Composable
fun CreateFlashcardContent(
    onCreateFlashcard: (String, String) -> Unit
){
    var word1 by remember { mutableStateOf("") }
    var word2 by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = word1,
            onValueChange = {word1 = it},
            label = {Text("Word 1")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = word2,
            onValueChange = { word2 = it },
            label = { Text("Word 2") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if(word1.isNotBlank() && word2.isNotBlank()){
                    onCreateFlashcard(word1, word2)
                    word1 = ""
                    word2 = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Create Flashcard")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CreateFlashcardContentPreview(){
    Box(modifier = Modifier.fillMaxSize()){

    }
}

