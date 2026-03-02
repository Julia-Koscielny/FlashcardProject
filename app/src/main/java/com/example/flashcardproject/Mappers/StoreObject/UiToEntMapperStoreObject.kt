package com.example.flashcardproject.Mappers.StoreObject

import com.example.flashcardproject.Data.StoreObject.StoreObjectEnt
import com.example.flashcardproject.Presentation.StoreObject.StoreObjectUi

fun StoreObjectUi.toEnt(): StoreObjectEnt =
    StoreObjectEnt(
        id = id,
        price = price,
        name = name
    )