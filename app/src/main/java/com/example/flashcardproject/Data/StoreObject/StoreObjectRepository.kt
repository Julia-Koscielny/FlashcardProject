package com.example.flashcardproject.Data.StoreObject

import com.example.flashcardproject.Presentation.StoreObject.StoreObjectUi
import kotlinx.coroutines.flow.Flow

interface StoreObjectRepository {

    val storeObjects: Flow<List<StoreObjectUi>>

    suspend fun insertStoreObject (storeObject: StoreObjectUi)

    suspend fun deleteStoreObject(storeObjectId: Long)

    fun getStoreObjectPrice(storeObjectId: Long): Flow<Int?>

}