package com.example.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "base", factory, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE words (id INTEGER PRIMARY KEY, rus TEXT, ens TEXT)")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS words")
        onCreate(db)
    }
    fun addWord(rus: String, eng: String) {
        val values = ContentValues()
        values.put("rus", rus)
        values.put("eng", eng)
        val db = this.writableDatabase
        db.insert("words", null, values)
        db.close()
    }
    fun getAllWords(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM words", null)
    }

}