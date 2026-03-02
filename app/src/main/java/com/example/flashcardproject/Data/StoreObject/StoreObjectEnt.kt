package com.example.flashcardproject.Data.StoreObject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "storeObject")
data class StoreObjectEnt(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "storeObjectId")
    val id: Long,

    @ColumnInfo(name = "storeObjectPrice")
    val price: Int,

    @ColumnInfo(name = "storeObjectName")
    val name: String
)




