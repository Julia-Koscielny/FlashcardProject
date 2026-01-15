package com.example.flashcardproject.Data.Flashcard


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.flashcardproject.Data.Folder.FolderEnt

@Entity(tableName = "flashcards",
    foreignKeys = [
        ForeignKey(
            entity = FolderEnt::class,
            parentColumns = ["folder_id"],
            childColumns = ["folder_id"],
            //When the folder is deleted, all flashcards are also deleted
            onDelete = ForeignKey.Companion.CASCADE
        )
    ],
    indices = [Index("folder_id")]
)
data class FlashcardEnt(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "flashcard_id")
    val id: Long = 0,

    @ColumnInfo(name = "folder_id")
    val folderId:Long,

    @ColumnInfo(name= "word1")
    val word1: String,

    @ColumnInfo(name= "word2")
    val word2: String,

    @ColumnInfo(name= "flashcard_state")
    val isUp: Boolean = true,

    @ColumnInfo(name= "flashcard_rep_date")
    val next_date: Int = 0,

    @ColumnInfo(name= "flashcard_price")
    val price_value: Int =0,

    @ColumnInfo(name= "flashcard_cr_date")
    val creation_date: Int = 0,
)