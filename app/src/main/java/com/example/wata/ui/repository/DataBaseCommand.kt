package com.example.wata.ui.repository

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
    class WordsDBOpenHelper(context: Context,
                               factory: SQLiteDatabase.CursorFactory?) :
        SQLiteOpenHelper(context, DATABASE_NAME,
            factory, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                    TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME
                    + " TEXT" + ")")
            db.execSQL(CREATE_PRODUCTS_TABLE)
        }
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }

        fun getName(wId : Int): Cursor? {
            val db = this.readableDatabase
            return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE id = $wId", null)
        }
        companion object {
            private val DATABASE_VERSION = 1
            private val DATABASE_NAME = "DataBaseWords.db"
            val TABLE_NAME = "Words"
            val COLUMN_ID = "id"
            val COLUMN_NAME = "name"
        }
    }