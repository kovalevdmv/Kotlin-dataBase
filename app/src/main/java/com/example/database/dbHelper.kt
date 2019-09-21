package com.example.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import android.widget.Toast.*
import java.lang.Exception
import kotlin.math.log
import kotlin.math.round

class dbHelper(val context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, "base", factory, 1) {

    enum class ADD_STATUS {
        ADDED, EXIST;
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE words (id INTEGER PRIMARY KEY, rus TEXT, eng TEXT)")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS words")
        onCreate(db)
    }

    fun addWord(word:WordData) : ADD_STATUS {
        if (getWordByRus(word.rus)?.count ?:0 > 0) {
            Toast.makeText(context, "EXIST", Toast.LENGTH_SHORT).show()
            return ADD_STATUS.EXIST
        }
        val values = ContentValues()
        values.put("rus", word.rus)
        values.put("eng", word.eng)
        val db = this.writableDatabase
        db.insert("words", null, values)
        db.close()
        Toast.makeText(context, "ADDED", Toast.LENGTH_SHORT).show()
        return ADD_STATUS.ADDED
    }

    fun getAllWords(): Cursor? {
        val db = this.readableDatabase
        val r = db.rawQuery("SELECT * FROM words", null)
        return r;
    }

    fun getWordByRus(rus:String): Cursor?{
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM words WHERE rus=\"$rus\"", null)
    }

    fun getVariation():ArrayList<WordData>{
        val res = ArrayList<WordData>()
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM words", null)
        for (i in 1..4) {
            val RandPos = round(Math.random() * c.count-1).toInt()
            c.moveToPosition(RandPos)
            try {
                res.add(WordData(cursor_get(c, "rus"), cursor_get(c, "eng")))
            } catch (e: Exception){
                Log.println(Log.ERROR,"getVariation",e.toString())
                res.add(WordData("", ""))
            }
        }
        return res
    }

    fun cursor_get(cursor:Cursor?, Name:String):String{
        try {
            return cursor?.getString(cursor.getColumnIndex(Name)) ?: ""
        }catch (e: Exception){
            Log.println(Log.ERROR,"cursor_getfg",e.toString())
            return ""
        }
    }

    fun allDel(){
        val db = this.readableDatabase
        db.execSQL("DELETE FROM words")
        Toast.makeText(context, "all delete", Toast.LENGTH_SHORT).show()
    }


}