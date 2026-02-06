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
import com.example.flashcardproject.Presentation.Composables.CreateFlashcardScreen
import com.example.flashcardproject.Presentation.Composables.FoldersInterfaceScreen
import com.example.flashcardproject.Presentation.Composables.NavBarScreen
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

    Column(modifier = Modifier.
    padding(5.dp)) {
        NavBarScreen()
        FoldersInterfaceScreen()
    }


}

