package com.example.wata.ui.repository
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface WordDAO {
    @Insert(onConflict = REPLACE)
    suspend fun save(wordEntity: WordEntity)

    @Query("SELECT * FROM words WHERE `id`=:id")
    suspend fun getByID(id: Long): WordEntity?
}