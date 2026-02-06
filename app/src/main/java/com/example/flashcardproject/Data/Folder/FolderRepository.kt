package com.example.flashcardproject.Data.Folder

interface FolderRepository {
    suspend fun insertFolder(folderEnt: FolderEnt)
    suspend fun updateFolder(folderEnt: FolderEnt)
    suspend fun deleteFolder(folderEnt: FolderEnt)

}