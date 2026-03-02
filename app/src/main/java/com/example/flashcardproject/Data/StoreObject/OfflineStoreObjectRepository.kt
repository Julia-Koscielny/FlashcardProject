package com.example.flashcardproject.Data.StoreObject

import com.example.flashcardproject.Mappers.StoreObject.toEnt
import com.example.flashcardproject.Mappers.StoreObject.toUI
import com.example.flashcardproject.Presentation.StoreObject.StoreObjectUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineStoreObjectRepository(
    private val StoreObjectDAO: StoreObjectDAO
): StoreObjectRepository {

    override val storeObjects: Flow<List<StoreObjectUi>> =
        StoreObjectDAO.getStoreObjects()
            .map { list ->
                list.map {it.toUI()}
            }

    override suspend fun insertStoreObject(storeObject: StoreObjectUi) = StoreObjectDAO.insertStoreObject(storeObject.toEnt())

    override suspend fun deleteStoreObject(storeObjectId: Long) = StoreObjectDAO.deleteStoreObject(storeObjectId)

    override fun getStoreObjectPrice(storeObjectId: Long): Flow<Int?> = StoreObjectDAO.getStoreObjectPrice(storeObjectId)

}