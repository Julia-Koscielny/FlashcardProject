package com.example.flashcardproject.Mappers.User

import com.example.flashcardproject.Data.User.UserEnt
import com.example.flashcardproject.Presentation.User.UserUi
import kotlin.Long

fun UserEnt.toUI(): UserUi =
    UserUi(
        id = id,
        points = points.toString()
    )