package com.example.flashcardproject.Presentation.Folder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashcardproject.Data.Flashcard.FlashcardRepository
import com.example.flashcardproject.Data.Folder.FolderRepository

class FolderViewModelFactory (
    private val folderRepository: FolderRepository,
    private val flashcardRepository: FlashcardRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FolderViewModel::class.java)){
            return FolderViewModel(folderRepository, flashcardRepository ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}