package com.example.flashcardproject.Data.Folder

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.flashcardproject.Data.Flashcard.FlashcardEnt
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDAO {
    @Insert
    suspend fun insertFolder(folderEnt: FolderEnt)

    @Update
    suspend fun updateFolder(folderEnt: FolderEnt)

    @Query("DELETE FROM folder WHERE folder_id = :id")
    suspend fun deleteFolder(id: Long)

    @Query("SELECT * FROM folder")
    fun getFolders(): Flow<List<FolderEnt>>
}