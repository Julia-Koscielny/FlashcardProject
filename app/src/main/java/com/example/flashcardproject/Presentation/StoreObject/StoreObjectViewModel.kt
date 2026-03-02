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

    private val predefinedItems = listOf(
        StoreObjectUi(id = 1L, name = "Plant 1", price = 20),
        StoreObjectUi(id = 2L, name = "Plant 2", price = 40),
        StoreObjectUi(id = 3L, name = "Plant 3", price = 60)
    )

    val storeObjectUiState: StateFlow<StoreObjectUiState> =
        repository.storeObjects
            .map { dbObjects ->
                val allItems = predefinedItems + dbObjects
                StoreObjectUiState(storeObjects = allItems)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = StoreObjectUiState(storeObjects = predefinedItems)
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