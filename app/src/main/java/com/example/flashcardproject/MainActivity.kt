package com.example.flashcardproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.flashcardproject.ui.theme.FlashcardProjectTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.flashcardproject.Data.AppDatabase
import com.example.flashcardproject.Data.Flashcard.OfflineFlashcardsRepository
import com.example.flashcardproject.Data.Folder.OfflineFolderRepository
import com.example.flashcardproject.Data.StoreObject.OfflineStoreObjectRepository
import com.example.flashcardproject.Data.User.OfflineUserRepository
import com.example.flashcardproject.Navigation.FlashcardsAppNavGraph
import com.example.flashcardproject.Presentation.Composables.NavBarContent
import com.example.flashcardproject.Presentation.Composables.NavBarScreen
import com.example.flashcardproject.Presentation.User.UserViewModel
import com.example.flashcardproject.Presentation.User.UserViewModelFactory


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("APP_DEBUG", "MainActivity started")

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("CRASH", "Uncaught exception", throwable)
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val database = AppDatabase.getDatabase(applicationContext)
            val userRepository = OfflineUserRepository(database.userDAO())

            setContent {

                val userViewModel: UserViewModel = viewModel(
                    factory = UserViewModelFactory(userRepository)
                )

                LaunchedEffect(Unit) {
                    userViewModel.ensureUserExists(1L)
                }
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
    fun FlashcardsApp() {
        val context = LocalContext.current
        val app = context.applicationContext as FlashcardApp

        val folderRepository = remember {
            OfflineFolderRepository(app.database.folderDAO())
        }

        val flashcardRepository = remember {
            OfflineFlashcardsRepository(app.database.flashcardDAO())
        }

        val userRepository = remember {
            OfflineUserRepository(app.database.userDAO())
        }

        val storeObjectRepository = remember {
            OfflineStoreObjectRepository(app.database.storeObjectDAO())
        }

        val navController = rememberNavController()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            NavBarContent(
                onClick = { index ->
                    when (index) {
                        0 -> navController.navigate("folders")
                        1 -> navController.navigate("user/1")
                        3 -> navController.navigate("Shop")
                    }
                }
            )
            FlashcardsAppNavGraph(
                modifier = Modifier.weight(1f),
                folderRepository = folderRepository,
                flashcardRepository = flashcardRepository,
                userRepository = userRepository,
                storeObjectRepository = storeObjectRepository,
                navController = navController
            )
        }
    }
}

