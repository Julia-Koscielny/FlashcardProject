package com.example.flashcardproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcardproject.Presentation.Flashcard.FlashcardViewModel
import com.example.flashcardproject.ui.theme.FlashcardProjectTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.flashcardproject.Data.AppDatabase
import com.example.flashcardproject.Data.Flashcard.FlashcardRepository
import com.example.flashcardproject.Data.Flashcard.OfflineFlashcardsRepository
import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi
import com.example.flashcardproject.Presentation.Flashcard.FlashcardViewModelFactory
import com.example.flashcardproject.Presentation.Folder.FolderViewModel
import com.example.flashcardproject.Presentation.Folder.FolderUi


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlashcardProjectTheme {
                Scaffold { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        FlashcardsApp()
                    }
                }

            }
        }
    }
}

// TOP-LVL APP COMPOSABLE
@Composable
fun FlashcardsApp(){
    val context = LocalContext.current

    val dataBase = remember {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    val repository = remember {
        OfflineFlashcardsRepository(dataBase .flashcardDAO())
    }

    val flashcardViewModel: FlashcardViewModel = viewModel(
        factory = FlashcardViewModelFactory(repository)
    )

    CreateFlashcardScreen(
        viewModel = flashcardViewModel
    )
}


// FOLDERS INTERFACE
@Composable
fun FoldersInterfaceScreen(
    viewModel: FolderViewModel = viewModel()
) {
    val uiState by viewModel.flashCardsAppUiState.collectAsState()

    if (uiState.folders.isEmpty()){
        Text("No Folders")
    }else {
        FoldersInterfaceContent(
            folderList = uiState.folders
        )
    }


    fun addFolder(){
        viewModel.addFolder(name = "name")
    }
}


@Composable
private fun FoldersInterfaceContent(
    folderList: List<FolderUi>
){
    LazyColumn {
        items(items = folderList,
            key = { it.id }){
                folder ->
            FolderMin(folder)
        }
    }
}

@Composable
fun FolderMin(folder: FolderUi){

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
        Text( text = folder.name,
            modifier = Modifier.padding(16.dp))
    }
}


// FOLDER INSIDE

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


    // SINGULAR FLASHCARD
    @Composable
    fun SingularFlashcardScreen(flashcard: FlashcardUi, onFlip: () -> Unit) {
        SingularFlashcardContent(
            word = if (flashcard.isUp) flashcard.word1 else flashcard.word2,
            onFlip = onFlip
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
                modifier = Modifier.padding(16.dp),
                onClick = onFlip
            ) {
                Text(
                    text = word,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }

// CREATING FLASHCARDS
@Composable
fun CreateFlashcardScreen(viewModel: FlashcardViewModel){
    CreateFlashcardContent (
        onCreateFlashcard = { word1, word2 ->
            viewModel.insertFlashcard(
                FlashcardUi(
                    id = 0,
                    word1 = word1,
                    word2 = word2,
                    folderId = 0,
                    isUp = true
                )
            )
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
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Flashcard")
        }

    }
}




//PREVIEWS

@Preview(showBackground = true)
@Composable
fun CreateFlashcardContentPreview(){
    CreateFlashcardContent(
        onCreateFlashcard = {word1, word2 ->
            println("Preview: $word1 - $word2")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun  SingularFlashcardScreenPreview(){
    SingularFlashcardContent(
        word = "Hello",
        onFlip = {}
    )
}



