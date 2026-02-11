package com.example.flashcardproject.Presentation.Folder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardproject.Data.Folder.FolderRepository
import com.example.flashcardproject.FlashCardsAppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.flashcardproject.Mappers.Folder.toEntity
class FolderViewModel(private val folderRepository: FolderRepository): ViewModel() {
    private val _FlashCardsApp_uiState = MutableStateFlow(FlashCardsAppUiState(emptyList()))
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

    fun addFolder(folder: FolderUi){
       viewModelScope.launch { folderRepository.insertFolder(folder.toEntity()) }
   }

   fun deleteFolder( id: Long ){
       viewModelScope.launch { folderRepository.deleteFolder(id) }
   }
}