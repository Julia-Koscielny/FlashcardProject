package com.example.flashcardproject.Presentation.Folder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardproject.Data.Flashcard.FlashcardRepository
import com.example.flashcardproject.Data.Folder.FolderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.flashcardproject.Mappers.Folder.toEntity
import kotlinx.coroutines.flow.update

class FolderViewModel(
    private val folderRepository: FolderRepository,
    private val flashcardRepository: FlashcardRepository
): ViewModel() {
    private val _folder_UiState = MutableStateFlow(FolderStateUi())
    val uiState: StateFlow<FolderStateUi> = _folder_UiState

    init {
        observeFolders()
    }

    private fun observeFolders(){
        viewModelScope.launch {
            folderRepository.folders.collect { folders ->
                _folder_UiState.update { current ->
                    current.copy(folders = folders)
                }
            }
        }
    }

    fun loadFolderFlashcards(folderId: Long){
        viewModelScope.launch {
            flashcardRepository
                .getFolderFlashcard(folderId)
                .collect { flashcards ->
                    _folder_UiState.update { current ->
                        current.copy(
                            selectedFolderId = folderId,
                            flashcards = flashcards
                        )
                    }
                }
        }
    }

    fun addFolder(folder: FolderUi){
       viewModelScope.launch { folderRepository.insertFolder(folder.toEntity()) }
   }

   fun deleteFolder( id: Long ){
       viewModelScope.launch { folderRepository.deleteFolder(id) }
   }

}