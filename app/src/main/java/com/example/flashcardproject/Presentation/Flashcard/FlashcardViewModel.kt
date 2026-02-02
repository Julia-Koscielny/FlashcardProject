package com.example.flashcardproject.Presentation.Flashcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardproject.Data.Flashcard.FlashcardRepository
import com.example.flashcardproject.Presentation.Folder.FolderUi
import com.example.flashcardproject.FlashCardsAppUiState
import com.example.flashcardproject.Mappers.Flashcard.toEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FlashcardViewModel(private val flashcardRepository: FlashcardRepository): ViewModel()  {

    private val _FlashCardsApp_uiState = MutableStateFlow(FlashCardsAppUiState(emptyList()))
    var flashCardsAppUiState : StateFlow<FlashCardsAppUiState> =
        flashcardRepository.flashcards
            .map{ flashcards ->
                FlashCardsAppUiState(flashcards = flashcards)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = FlashCardsAppUiState()
            )

    fun insertFlashcard (flashcard : FlashcardUi){
        viewModelScope.launch { flashcardRepository.insertFlashcard(flashcard.toEntity()) }
    }


    fun deleteFlashcard  (flashcard: FlashcardUi){
        viewModelScope.launch {
            flashcardRepository.deleteFlashcard(flashcard.toEntity())
        }
    }

    fun updateWord1(id: Long, newWord: String){
        viewModelScope.launch {
            flashcardRepository.updateWord1(id,newWord)
        }
    }

    fun updateWord2(id: Long, newWord: String) {
        viewModelScope.launch {
            flashcardRepository.updateWord2(id,newWord)
        }
    }

    fun turnFlashcard(isUp: Boolean, id: Long) {
        viewModelScope.launch {
            flashcardRepository.turnFlashcard(isUp, id)
        }

    }

    fun moveCardToFolder(id: Long, folder: Long){
        viewModelScope.launch {
            flashcardRepository.moveFlashcardToFolder(id, folder)
        }
    }


}