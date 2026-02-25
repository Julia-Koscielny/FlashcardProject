package com.example.flashcardproject.Data.User

import androidx.room.Dao
import androidx.room.Query
import com.example.flashcardproject.Data.Flashcard.FlashcardEnt
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO{

    @Query("SELECT * FROM user")
    fun getUsers(): Flow<List<UserEnt>>

    @Query("SELECT userPoints FROM  user WHERE userId = :userId")
    fun getUserPoints(userId: Long): Flow<Int>

    @Query("UPDATE user SET userPoints = userPoints + :points WHERE userId = :userId")
    suspend fun updatePoints(userId: Long, points: Int)
}