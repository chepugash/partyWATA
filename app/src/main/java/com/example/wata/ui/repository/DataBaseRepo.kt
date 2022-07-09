package com.example.wata.ui.repository

import android.content.Context

class DataBaseRepo private constructor(private val database: DataBaseWords) {
    companion object {
        private var INSTANCE: DataBaseRepo? = null

        fun get(context: Context): DataBaseRepo {
            if (INSTANCE == null) {
                INSTANCE = DataBaseRepo(DataBaseWords.get(context))
            }
            return INSTANCE as DataBaseRepo
        }
    }
    suspend fun savePerson(word: Word) =
        database.wordDAO().save(WordMapper.toEntity(word))

    suspend fun getById(id: Long) = database.wordDAO().getByID(id)?.let { WordMapper.toDTO(it) }
}