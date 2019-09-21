package com.example.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import android.widget.Toast.*

class dbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, "base", factory, 1) {

    enum class ADD_STATUS {
        ADDED, EXIST;

        }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE words (id INTEGER PRIMARY KEY, rus TEXT, ens TEXT)")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS words")
        onCreate(db)
    }

    fun addWord(word:WordData) : ADD_STATUS {
        if (getWordByRus(word.rus)?.count ?:0 > 0) {
            return ADD_STATUS.EXIST
        }
        val values = ContentValues()
        values.put("rus", word.rus)
        values.put("eng", word.eng)
        val db = this.writableDatabase
        db.insert("words", null, values)
        db.close()
        return ADD_STATUS.ADDED
    }

    fun getAllWords(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM words", null)
    }

    fun getWordByRus(rus:String): Cursor?{
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM words WHERE rus=\"$rus\"", null)
    }



}