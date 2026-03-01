package com.example.flashcardproject.Data.User

import com.example.flashcardproject.Mappers.User.toEnt
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

    override suspend fun insertUser(user: UserUi) {
        userDAO.insertUser(user.toEnt())
    }

    override fun getUserPoints(userId: Long): Flow<Int> =
        userDAO.getUserPoints(userId)

    override suspend fun updatePoints(userId: Long, points: Int): Int? {
        return userDAO.updatePoints(userId, points)
    }

    override suspend fun getPointsOnce(userId: Long): Int {
       return userDAO.getPointsOne(userId)
    }

    override suspend fun userExists(userId: Long): Boolean {
        return userDAO.userExists(userId)
    }
}