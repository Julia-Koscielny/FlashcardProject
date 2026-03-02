package com.example.flashcardproject.Mappers.StoreObject

import com.example.flashcardproject.Data.Resources.AppIcons
import com.example.flashcardproject.Data.StoreObject.StoreObjectEnt
import com.example.flashcardproject.Presentation.StoreObject.StoreObjectUi

fun StoreObjectEnt.toUI(): StoreObjectUi =
    StoreObjectUi(
        id = id,
        price = price,
        name = name
    )