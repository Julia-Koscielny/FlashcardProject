package com.example.flashcardproject.Data.User

import com.example.flashcardproject.Mappers.User.toUI
import com.example.flashcardproject.Presentation.User.UserUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineUserRepository(private val userDAO: UserDAO): UserRepository {
    override val users: Flow<List<UserUi>>  =
        userDAO.getUsers()
            .map { list ->
                list.map{it.toUI()}
            }

    override fun getUserPoints(userId: Long): Flow<Int> =
        userDAO.getUserPoints(userId)

    override suspend fun updatePoints(userId: Long, points: Int) = userDAO.updatePoints(userId,points)
}