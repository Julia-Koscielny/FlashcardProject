package com.example.flashcardproject.Mappers.User

import com.example.flashcardproject.Data.User.UserEnt
import com.example.flashcardproject.Presentation.User.UserUi

fun UserUi.toEnt(): UserEnt =
    UserEnt(
        id = id,
        points = points.toInt()
    )
