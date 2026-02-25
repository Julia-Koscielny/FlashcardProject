package com.example.flashcardproject.Data.User

import com.example.flashcardproject.Presentation.User.UserUi
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val users: Flow<List<UserUi>>

    fun getUserPoints(userId: Long): Flow<Int>

    suspend fun updatePoints(userId: Long, points: Int)
}