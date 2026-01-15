package com.example.flashcardproject.Presentation.Flashcard

import androidx.lifecycle.ViewModel
import com.example.flashcardproject.Presentation.Folder.FolderUi
import com.example.flashcardproject.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FlashcardViewModel(): ViewModel()  {
    private val _uiState = MutableStateFlow(UiState(emptyList()))
    var uiState : StateFlow<UiState> = _uiState.asStateFlow()


    fun createFlashcard (flashcard : FlashcardUi){
        val currentState = _uiState.value
        val currentList : List<FlashcardUi> = currentState.flashcards
        val newList: List<FlashcardUi> = currentList + flashcard
        _uiState.value = currentState.copy(flashcards = newList)
    }

    fun deleteFlashcard  (flashcard: FlashcardUi){
        val currentState = _uiState.value
        val currentList : List<FlashcardUi> = currentState.flashcards
        val newList: List<FlashcardUi> = currentList - flashcard
        _uiState.value = currentState.copy(flashcards = newList)
    }

    fun updateWord1(flashcard: FlashcardUi, newWord: String){
        val state = _uiState.value
        val newList = state.flashcards.map {
            if (it.id == flashcard.id) it.copy(word1 = newWord)
            else it
        }
        _uiState.value = state.copy(flashcards = newList)
    }

    fun updateWord2(flashcard: FlashcardUi, newWord: String) {
        val state = _uiState.value
        val newList = state.flashcards.map {
            if (it.id == flashcard.id) it.copy(word2 = newWord)
            else it
        }
        _uiState.value = state.copy(flashcards = newList)
    }

    fun turnFlashcard(flashcard: FlashcardUi) {
        val current = _uiState.value
        val newList = current.flashcards.map {
            if (it == flashcard) it.copy(isUp = !it.isUp)
            else it
        }
        _uiState.value = current.copy(flashcards = newList)
    }

    fun moveCardToFolder(flashcard: FlashcardUi, folder: FolderUi){
        val state = _uiState.value
        val uptFlashcards = state.flashcards.map {
            if(it.id == flashcard.id) flashcard.copy(folderId = folder.id)
            else it
        }
        _uiState.value = state.copy(flashcards = uptFlashcards )
    }

}