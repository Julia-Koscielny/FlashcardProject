package com.example.flashcardproject.Presentation.Folder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashcardproject.Data.Folder.FolderRepository
import com.example.flashcardproject.Presentation.Folder.FolderViewModel

class FolderViewModelFactory (private val repository: FolderRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FolderViewModel::class.java)){
            return FolderViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}