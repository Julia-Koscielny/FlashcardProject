package com.example.flashcardproject.Mappers.Folder

import com.example.flashcardproject.Data.Folder.FolderEnt
import com.example.flashcardproject.Presentation.Folder.FolderUi

fun FolderUi.toEntity(): FolderEnt =
    FolderEnt(
        id = id,
        name = name
    )