package com.example.wata.ui.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
    )
