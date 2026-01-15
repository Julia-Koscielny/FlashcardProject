package com.example.flashcardproject.Domain

data class Flashcard(
    val id: Long,
    val word1: String,
    val word2: String,
    val isUp: Boolean = true,
    val folderId : Long,
    val next_date: Int =0,
    val price_value: Int=0,
    val creation_date: Int=0
)