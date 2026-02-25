package com.example.flashcardproject.Presentation.Flashcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashcardproject.Data.Flashcard.FlashcardRepository
import com.example.flashcardproject.Data.Folder.FolderRepository
import com.example.flashcardproject.Data.User.UserRepository

class FlashcardViewModelFactory(
    private val flashcardRepository: FlashcardRepository,
    private val folderRepository: FolderRepository,
    private val userRepository: UserRepository
) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlashcardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FlashcardViewModel(flashcardRepository, folderRepository, userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}