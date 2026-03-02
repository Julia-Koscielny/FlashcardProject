package com.example.flashcardproject.Presentation.StoreObject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardproject.Data.StoreObject.StoreObjectRepository
import com.example.flashcardproject.Mappers.StoreObject.toEnt
import com.example.flashcardproject.Presentation.Folder.FolderStateUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StoreObjectViewModel(private val repository: StoreObjectRepository) : ViewModel() {

    val storeObjectUiState: StateFlow<StoreObjectUiState> =
        repository.storeObjects
            .map { objects ->
                StoreObjectUiState(storeObjects = objects)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = StoreObjectUiState()
            )

    fun insertStoreObject(storeObject: StoreObjectUi){
        viewModelScope.launch {
            repository.insertStoreObject(storeObject)
        }
    }

    fun deleteStoreObject(id: Long){
        viewModelScope.launch {
            repository.deleteStoreObject(id)
        }
    }

    fun getStoreObjectPrice(id: Long): Flow<Int?>{
        return repository.getStoreObjectPrice(id)
    }
}