package com.example.flashcardproject.Presentation.Folder

import com.example.flashcardproject.Data.Resources.Icons
import androidx.lifecycle.ViewModel
import com.example.flashcardproject.FlashCardsAppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FolderViewModel(): ViewModel() {
    private val _FlashCardsApp_uiState = MutableStateFlow(FlashCardsAppUiState(emptyList()))
    var flashCardsAppUiState : StateFlow<FlashCardsAppUiState> = _FlashCardsApp_uiState.asStateFlow()

    fun addFolder(name: String, id:Long, icon: Icons){
       val state = _FlashCardsApp_uiState.value
       val newFolder = FolderUi(id = id, name = name, icon = icon)
        _FlashCardsApp_uiState.value = state.copy(folders = state.folders + newFolder)
   }

   fun deleteFolder( id:Long ){
       val state = _FlashCardsApp_uiState.value

       _FlashCardsApp_uiState.value = state.copy(
           folders = state.folders.filterNot {it.id ==id})
   }


}