package com.example.flashcardproject.ui.theme

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flashcardproject.FlashCardsAppUiState
import com.example.flashcardproject.Presentation.Flashcard.FlashcardViewModel
import com.example.flashcardproject.Presentation.Folder.FolderViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/*object ViewModelProvider {
    private val _FlashCardsApp_uiState = MutableStateFlow(FlashCardsAppUiState(emptyList()))
    var flashCardsAppUiState : StateFlow<FlashCardsAppUiState> = _FlashCardsApp_uiState.asStateFlow()
    val Factory = viewModelFactory {
        initializer {
            FolderViewModel()
        }

        initializer {
            FlashcardViewModel()
        }
    }
}
/*
 */