package com.example.flashcardproject.Data.StoreObject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreObjectDAO{

    @Insert
    suspend fun insertStoreObject (storeObjectEnt: StoreObjectEnt)

    @Query("DELETE FROM storeObject WHERE storeObjectId = :storeObjectId")
    suspend fun deleteStoreObject(storeObjectId: Long)

    @Query("SELECT storeObjectPrice FROM storeObject WHERE storeObjectId = :storeObjectId")
    fun getStoreObjectPrice(storeObjectId: Long): Flow<Int?>

    @Query("SELECT * FROM storeObject")
    fun getStoreObjects(): Flow<List<StoreObjectEnt>>
}