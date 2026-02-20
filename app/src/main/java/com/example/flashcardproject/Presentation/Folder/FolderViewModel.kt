package com.example.flashcardproject.Presentation.Folder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardproject.Data.Flashcard.FlashcardRepository
import com.example.flashcardproject.Data.Folder.FolderRepository
import com.example.flashcardproject.FlashCardsAppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.flashcardproject.Mappers.Folder.toEntity
import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi
import kotlinx.coroutines.flow.asStateFlow

class FolderViewModel(
    private val folderRepository: FolderRepository,
    private val flashcardRepository: FlashcardRepository
): ViewModel() {
    var flashCardsAppUiState : StateFlow<FlashCardsAppUiState> =
        folderRepository.folders
            .map { folders ->
                FlashCardsAppUiState(folders = folders)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = FlashCardsAppUiState()
            )

    private val _folderFlashcards =
        MutableStateFlow<List<FlashcardUi>>(emptyList())

    val folderFlashcards: StateFlow<List<FlashcardUi>> =
        _folderFlashcards.asStateFlow()

    fun addFolder(folder: FolderUi){
       viewModelScope.launch { folderRepository.insertFolder(folder.toEntity()) }
   }

   fun deleteFolder( id: Long ){
       viewModelScope.launch { folderRepository.deleteFolder(id) }
   }

    fun loadFolderFlashcards(folderId: Long){
        viewModelScope.launch {
            flashcardRepository
                .getFolderFlashcard(folderId)
                .collect { flashcards ->
                    _folderFlashcards.value = flashcards
                }
        }
    }
}