package com.example.flashcardproject.Data.User

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEnt(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    val id: Long,

    @ColumnInfo(name = "userPoints")
    val points: Int
)
