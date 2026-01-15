package com.example.flashcardproject.Data.Folder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folder")
data class FolderEnt(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "folder_id")
    val id: Long = 0,

    @ColumnInfo(name= "folder_name")
    val name: String,

    @ColumnInfo(name= "folder_cr_date")
    val creation_date: Int = 0
)