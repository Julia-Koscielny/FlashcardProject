package com.example.flashcardproject.Presentation.Flashcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardproject.Data.Flashcard.FlashcardRepository
import com.example.flashcardproject.Data.Folder.FolderRepository
import com.example.flashcardproject.Data.User.UserRepository
import com.example.flashcardproject.Presentation.Flashcard.FlashcardUiState
import com.example.flashcardproject.Mappers.Flashcard.toEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FlashcardViewModel(
    private val flashcardRepository: FlashcardRepository,
    private val folderRepository: FolderRepository,
    private val userRepository: UserRepository): ViewModel()  {

    private val _FlashCardsApp_uiState = MutableStateFlow(FlashcardUiState(emptyList()))
    var flashcardUiState : StateFlow<FlashcardUiState> = _FlashCardsApp_uiState


    init {
        viewModelScope.launch {
            flashcardRepository.flashcards.collect { flashcards ->
                _FlashCardsApp_uiState.value = FlashcardUiState(flashcards)
            }
        }
    }


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

    fun turnFlashcard(flashcard: FlashcardUi) {
        viewModelScope.launch {
            flashcardRepository.turnFlashcard(
                isUp = !flashcard.isUp,
                id = flashcard.id
            )
        }
    }

    fun moveCardToFolder(id: Long, folder: Long){
        viewModelScope.launch {
            flashcardRepository.moveFlashcardToFolder(id, folder)
        }
    }

    fun onFolderCompleted (userId: Long){
        viewModelScope.launch {
            userRepository.updatePoints(userId,10)

        }
    }

    fun getFolderFlashcards( folderId: Long) =
        flashcardRepository.getFolderFlashcard(folderId)

}