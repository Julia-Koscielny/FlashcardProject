package com.example.flashcardproject.Data.Folder

class OfflineFolderRepository(private val folderDAO: FolderDAO): FolderRepository {
    override suspend fun deleteFolder(folderEnt: FolderEnt) = folderDAO.deleteFolder(folderEnt)

    override suspend fun insertFolder(folderEnt: FolderEnt) = folderDAO.insertFolder(folderEnt)

    override suspend fun updateFolder(folderEnt: FolderEnt) = folderDAO.updateFolder(folderEnt)
}