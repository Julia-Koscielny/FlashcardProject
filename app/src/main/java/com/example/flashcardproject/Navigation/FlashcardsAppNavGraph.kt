package com.example.flashcardproject.Navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flashcardproject.Data.Folder.FolderRepository
import com.example.flashcardproject.Presentation.Composables.CreateFolderScreen
import com.example.flashcardproject.Presentation.Composables.DeleteFolderScreen
import com.example.flashcardproject.Presentation.Composables.FoldersInterfaceScreen
import com.example.flashcardproject.Presentation.Folder.FolderViewModel
import com.example.flashcardproject.Presentation.Folder.FolderViewModelFactory

@Composable
fun FlashcardsAppNavGraph(
    modifier: Modifier = Modifier,
    folderRepository: FolderRepository
){
    val navController = rememberNavController()

    val folderViewModelFactory = remember {
        FolderViewModelFactory(folderRepository)
    }

    val folderViewModel: FolderViewModel = viewModel(
        factory = folderViewModelFactory
    )

    NavHost(
        navController = navController,
        startDestination = "folders",
        modifier = modifier
    ){
        composable("folders"){
            FoldersInterfaceScreen(
                viewModel = folderViewModel,
                onAddFolderClick = {
                    navController.navigate("createFolder")
                },
                onDeleteFolderClick = { folderId ->
                    navController.navigate("deleteFolder/$folderId")
                }
            )
        }

        composable("createFolder"){
            CreateFolderScreen(
                viewModel = folderViewModel,
                onFolderCreated = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            "deleteFolder/{folderId}",
            arguments = listOf(
                navArgument("folderId"){
                    type = NavType.LongType
                }
            )
        ){ backStackEntry ->
            val folderId = backStackEntry.arguments?.getLong("folderId")?: error("folderId missing")

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
    }
}