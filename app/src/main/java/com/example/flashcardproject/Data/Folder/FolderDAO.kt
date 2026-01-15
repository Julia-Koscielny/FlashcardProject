package com.example.flashcardproject.Data.Folder

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface FolderDAO {
    @Insert
    suspend fun insertFolder(folderEnt: FolderEnt)

    @Update
    suspend fun updateFolder(folderEnt: FolderEnt)

    @Delete
    suspend fun deleteFolder(folderEnt: FolderEnt)
}