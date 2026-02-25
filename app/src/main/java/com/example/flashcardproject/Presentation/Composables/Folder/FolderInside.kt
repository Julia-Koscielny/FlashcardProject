package com.example.flashcardproject.Presentation.Composables.Folder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi
import com.example.flashcardproject.Presentation.Folder.FolderViewModel

@Composable
fun FolderInsideScreen(
    viewModel: FolderViewModel,
    onPlayClick: () -> Unit,
    onFlashcardClick: () -> Unit,
    onCreateClick: () -> Unit,
    folderId: Long
) {
    LaunchedEffect(folderId) {
        viewModel.loadFolderFlashcards(folderId)
    }
    val uiState by viewModel.uiState.collectAsState()
    val flashcards = uiState.flashcards
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        FolderInsideContent(
            flashcardList = flashcards,
            onFlashcardClick = onFlashcardClick,
            modifier = Modifier.weight(1f)
        )

        PlayButton(
            onPlayClick = onPlayClick,
            modifier = Modifier
                .padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))

        CreateButton(
            onCreateClick = onCreateClick
        )
    }
}


@Composable
private fun FolderInsideContent(
    flashcardList: List<FlashcardUi>,
    onFlashcardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(
            items = flashcardList,
            key = { it.id }
        ) { flashcard ->

            FlashcardMin(
                flashcard = flashcard,
                onFlashcardClick = onFlashcardClick
            )

            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@Composable
fun PlayButton(
    onPlayClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(onClick = onPlayClick,
        modifier = modifier
            .height(90.dp)
            .width(160.dp)) {
        Text("PLAY")
    }
}

@Composable
fun CreateButton(
    onCreateClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(onClick = onCreateClick,
        modifier = modifier
            .height(90.dp)
            .width(160.dp)) {
        Text("CREATE")
    }

}


@Composable
fun FlashcardMin(
    flashcard: FlashcardUi,
    onFlashcardClick: () -> Unit
){

    Card (modifier = Modifier
        .height(90.dp)
        .width(240.dp),
        onClick = onFlashcardClick,

    ) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){

            Text( text = flashcard.word1,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FolderInsideContentPreview() {

    val flashcards = listOf<FlashcardUi>(
        FlashcardUi(23, "word1", "word2", true, 23),
        FlashcardUi(24, "word1", "word2", true, 25),
        FlashcardUi(22, "word1", "word2", true, 23),
        FlashcardUi(21, "word1", "word2", true, 25),
        FlashcardUi(12, "word1", "word2", true, 23),
        FlashcardUi(35, "word1", "word2", true, 25)
    )

    Column(modifier = Modifier.fillMaxSize()){
        FolderInsideContent(
            flashcardList = flashcards,
            onFlashcardClick = {}
        )
    }
}