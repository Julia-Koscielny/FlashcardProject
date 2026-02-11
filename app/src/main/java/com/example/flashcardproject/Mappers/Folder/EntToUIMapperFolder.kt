package com.example.flashcardproject.Mappers.Folder

import com.example.flashcardproject.Data.Flashcard.FlashcardEnt
import com.example.flashcardproject.Data.Folder.FolderEnt
import com.example.flashcardproject.Data.Resources.AppIcons
import com.example.flashcardproject.Presentation.Flashcard.FlashcardUi
import com.example.flashcardproject.Presentation.Folder.FolderUi

fun FolderEnt.toUI(): FolderUi =
    FolderUi(
        id = id,
        name = name,
    )
