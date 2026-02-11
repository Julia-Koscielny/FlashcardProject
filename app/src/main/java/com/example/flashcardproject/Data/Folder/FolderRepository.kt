package com.example.flashcardproject.Data.Folder

import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi
import com.example.flashcardproject.Presentation.Folder.FolderUi
import kotlinx.coroutines.flow.Flow

interface FolderRepository {
    val folders: Flow<List<FolderUi>>
    suspend fun insertFolder(folderEnt: FolderEnt)
    suspend fun updateFolder(folderEnt: FolderEnt)
    suspend fun deleteFolder(id: Long)

}