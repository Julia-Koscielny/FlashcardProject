package com.example.flashcardproject.Data.User

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flashcardproject.Data.Flashcard.FlashcardEnt
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEnt)

    @Query("SELECT * FROM user")
    fun getUsers(): Flow<List<UserEnt>>

    @Query("SELECT userPoints FROM  user WHERE userId = :userId")
    fun getUserPoints(userId: Long): Flow<Int>

    @Query("UPDATE user SET userPoints = userPoints + :points WHERE userId = :userId")
    suspend fun updatePoints(userId: Long, points: Int): Int?

    @Query("SELECT userPoints FROM user WHERE userId =:userId")
    suspend fun getPointsOne(userId: Long):Int

    @Query("SELECT EXISTS(SELECT 1 FROM user WHERE userId = :userId)")
    suspend fun userExists(userId: Long): Boolean
}