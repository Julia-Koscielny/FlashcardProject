package com.example.flashcardproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcardproject.Presentation.Flashcard.FlashcardViewModel
import com.example.flashcardproject.ui.theme.FlashcardProjectTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.flashcardproject.Data.AppDatabase
import com.example.flashcardproject.Data.Flashcard.OfflineFlashcardsRepository
import com.example.flashcardproject.Data.Folder.OfflineFolderRepository
import com.example.flashcardproject.Navigation.FlashcardsAppNavGraph
import com.example.flashcardproject.Presentation.Composables.NavBarScreen
import com.example.flashcardproject.Presentation.Flashcard.FlashcardViewModelFactory


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
    val app = context.applicationContext as FlashcardApp

    val folderRepository = remember {
        OfflineFolderRepository(app.database.folderDAO())
    }

    Column(modifier = Modifier.
    fillMaxSize()) {
        NavBarScreen()
        FlashcardsAppNavGraph(
            modifier = Modifier.weight(1f),
            folderRepository = folderRepository)
    }
}

