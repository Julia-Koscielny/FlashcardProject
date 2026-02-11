package com.example.flashcardproject.Data.Folder

import com.example.flashcardproject.Mappers.Flashcard.toUI
import com.example.flashcardproject.Presentation.Folder.FolderUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.flashcardproject.Mappers.Folder.toUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class OfflineFolderRepository(private val folderDAO: FolderDAO): FolderRepository {

    override val folders: Flow<List<FolderUi>> =
        folderDAO.getFolders()
            .map { list ->
                list.map { it.toUI()}
            }
            .flowOn(Dispatchers.IO)
    override suspend fun deleteFolder(id: Long) = folderDAO.deleteFolder(id)

    override suspend fun insertFolder(folderEnt: FolderEnt) = folderDAO.insertFolder(folderEnt)

    override suspend fun updateFolder(folderEnt: FolderEnt) = folderDAO.updateFolder(folderEnt)
}