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
    suspend fun moveFlashcardToFolder(id: Long, folderId: Long): Int

    //Select all flashcards from a given folder
    @Query("SELECT * FROM flashcards WHERE folder_id = :folderId ")
    fun getFolderFlashcard(folderId: Long): Flow<List<FlashcardEnt>>

    //Change word1
    @Query("UPDATE flashcards SET word1 = :word1 WHERE flashcard_id= :id")
    suspend fun updateWord1(id: Long, word1: String)

    //Change word2
    @Query("UPDATE flashcards SET word2 = :word2 WHERE flashcard_id= :id")
    suspend fun updateWord2(id: Long, word2: String)

    @Query("SELECT * FROM flashcards")
    fun getFlashcards(): Flow<List<FlashcardEnt>>

    @Query("UPDATE flashcards SET flashcard_state = :isUp WHERE flashcard_id =:id")
    suspend fun turnFLashcard(isUp: Boolean, id: Long)
}