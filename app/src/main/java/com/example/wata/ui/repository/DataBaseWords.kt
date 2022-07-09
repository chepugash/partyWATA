package com.example.wata.ui.repository
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [WordEntity::class], version = 1)
abstract class DataBaseWords : RoomDatabase() {
    companion object {
        private var INSTANCE: DataBaseWords? = null

        fun get(context: Context): DataBaseWords {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context, DataBaseWords::class.java, "database").build()
            }
            return INSTANCE as DataBaseWords
        }
    }
    abstract fun wordDAO(): WordDAO
}