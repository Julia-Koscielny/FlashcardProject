package com.example.flashcardproject.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flashcardproject.Data.Flashcard.FlashcardRepository
import com.example.flashcardproject.Data.Folder.FolderRepository
import com.example.flashcardproject.Presentation.Composables.Flashcard.CreateFlashcardScreen
import com.example.flashcardproject.Presentation.Composables.Folder.CreateFolderScreen
import com.example.flashcardproject.Presentation.Composables.Folder.DeleteFolderScreen
import com.example.flashcardproject.Presentation.Composables.Folder.FolderInsideScreen
import com.example.flashcardproject.Presentation.Composables.Folder.FoldersInterfaceScreen
import com.example.flashcardproject.Presentation.Flashcard.FlashcardViewModel
import com.example.flashcardproject.Presentation.Flashcard.FlashcardViewModelFactory
import com.example.flashcardproject.Presentation.Folder.FolderViewModel
import com.example.flashcardproject.Presentation.Folder.FolderViewModelFactory
import androidx.navigation.compose.NavHost
import com.example.flashcardproject.Data.User.UserRepository
import com.example.flashcardproject.Presentation.Composables.Flashcard.FlashcardPlayScreen
import com.example.flashcardproject.Presentation.Composables.NavBarContent
import com.example.flashcardproject.Presentation.Composables.User.UserInfoScreen
import com.example.flashcardproject.Presentation.User.UserViewModel
import kotlinx.coroutines.flow.first


@Composable
fun FlashcardsAppNavGraph(
    modifier: Modifier = Modifier,
    folderRepository: FolderRepository,
    flashcardRepository: FlashcardRepository,
    userRepository: UserRepository,
    navController: NavHostController
) {

    val folderViewModelFactory = remember {
        FolderViewModelFactory(folderRepository, flashcardRepository)
    }

    val folderViewModel: FolderViewModel = viewModel(
        factory = folderViewModelFactory
    )

    val flashcardViewModelFactory = remember {
        FlashcardViewModelFactory(flashcardRepository, folderRepository, userRepository)
    }

    val flashcardViewModel: FlashcardViewModel = viewModel(
        factory = flashcardViewModelFactory
    )

    val userViewModel = remember { UserViewModel(userRepository) }

    val currentUserId = 1L


    NavHost(
        navController = navController,
        startDestination = "folders",
        modifier = modifier
    ) {
        composable("folders") {
            FoldersInterfaceScreen(
                viewModel = folderViewModel,
                onAddFolderClick = {
                    navController.navigate("createFolder")
                },
                onDeleteFolderClick = { folderId ->
                    navController.navigate("deleteFolder/$folderId")
                },

                onEnterFolderClick = { folderId ->
                    navController.navigate("enterFolder/$folderId")
                }
            )
        }

        composable("createFolder") {
            CreateFolderScreen(
                viewModel = folderViewModel,
                onFolderCreated = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            "flashcardCreate/{folderId}",
            arguments = listOf(
                navArgument("folderId"){
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val folderId =
                backStackEntry.arguments?.getLong("folderId")
                    ?:error("Folder missing")

            CreateFlashcardScreen(
                viewModel = flashcardViewModel,
                onCreateClick = {navController.popBackStack()},
                folderId = folderId

            )
        }

        composable("enterFolder/{folderId}",
            arguments = listOf(
                navArgument("folderId"){
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val folderId =
                backStackEntry.arguments?.getLong("folderId")
                    ?: error("folderId missing")

            FolderInsideScreen(
                folderId = folderId,
                viewModel = folderViewModel,
                onPlayClick = {navController.navigate("flashcardPlay/$folderId")},
                onFlashcardClick = {},
                onCreateClick = {navController.navigate("flashcardCreate/$folderId")}
            )
        }

        composable(

            "deleteFolder/{folderId}",
            arguments = listOf(
                navArgument("folderId") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val folderId =
                backStackEntry.arguments?.getLong("folderId") ?: error("folderId missing")

            DeleteFolderScreen(
                viewModel = folderViewModel,
                folderId = folderId,
                onDeleted = {
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            "flashcardPlay/{folderId}",
            arguments = listOf(navArgument("folderId") { type = NavType.LongType })
        ) { backStackEntry ->

            val folderId = backStackEntry.arguments?.getLong("folderId") ?: error("FolderId missing")

            val flashcards by flashcardViewModel.getFolderFlashcards(folderId).collectAsState(initial = emptyList())

            FlashcardPlayScreen(
                flashcards = flashcards,
                viewModel = flashcardViewModel,
                onFinished = {flashcardViewModel.onFolderCompleted(currentUserId)
                    navController.navigate("enterFolder/$folderId")},
                onCancel = {navController.navigate("enterFolder/$folderId")}
            )
        }

        composable("user/{userId}",
            arguments = listOf(
                navArgument("userId"){type= NavType.LongType}
            )
        ){ backStackEntry ->
            val userId = backStackEntry.arguments?.getLong("userId") ?: 0L
            UserInfoScreen(userViewModel, userId )
        }

        composable("navBar"){
            NavBarContent(
                onClick = {index ->
                    when(index){
                        0 -> navController.navigate("folders")
                        1 -> navController.navigate("user/$currentUserId")
                        3 -> navController.navigate("")
                        4 -> navController.navigate("")
                    }
                }
            )
        }
    }
}

