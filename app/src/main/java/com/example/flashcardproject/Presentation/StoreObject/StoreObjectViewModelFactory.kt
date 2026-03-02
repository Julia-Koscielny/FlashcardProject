package com.example.flashcardproject.Presentation.StoreObject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashcardproject.Data.StoreObject.StoreObjectRepository

class StoreObjectViewModelFactory(
    private val repository: StoreObjectRepository
): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoreObjectViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return StoreObjectViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}