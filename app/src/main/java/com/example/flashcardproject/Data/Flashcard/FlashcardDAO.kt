package com.example.flashcardproject.Data.Flashcard


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDAO {
    @Insert
    suspend fun insertFlashcard(flashcardEnt: FlashcardEnt)

    @Update
    suspend fun updateFlashcard(flashcardEnt: FlashcardEnt)

    @Delete
    suspend fun deleteFlashcard(flashcardEnt: FlashcardEnt)

    //Moving flashcards to a different folder
    @Query("UPDATE flashcards SET folder_id = :folderId WHERE flashcard_id= :id ")
    suspend fun moveFlashcards(id: Long, folderId: Long): Int

    //Select all flashcards from a given folder
    @Query("SELECT * FROM flashcards WHERE folder_id = :folderId ")
    fun getFolderFlashcard(folderId: Long): Flow<List<FlashcardEnt>>
}