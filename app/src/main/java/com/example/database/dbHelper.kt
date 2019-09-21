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

class dbHelper(val context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, "base", factory, 2) {

    enum class ADD_STATUS {
        ADDED, EXIST;
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE words (id INTEGER PRIMARY KEY, rus TEXT, eng TEXT)")
        db.execSQL("CREATE TABLE stats (id INTEGER PRIMARY KEY, id_word INTEGER, ok INTEGER, err INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS words")
        onCreate(db)
    }

    fun addWord(word:WordData) : ADD_STATUS {
        if (getWordByRus(word.rus)?.count ?:0 > 0) {
            Toast.makeText(context, "Такое слово уже есть", Toast.LENGTH_SHORT).show()
            return ADD_STATUS.EXIST
        }
        val values = ContentValues()
        values.put("rus", word.rus)
        values.put("eng", word.eng)
        val db = this.writableDatabase
        db.insert("words", null, values)
        db.close()
        Toast.makeText(context, "Добавлено новое слово", Toast.LENGTH_SHORT).show()
        return ADD_STATUS.ADDED
    }

    fun getAllWords(): Cursor? {
        val db = this.readableDatabase
        val r = db.rawQuery(
            "SELECT " +
                    "W.id AS id, " +
                    "W.rus AS rus, " +
                    "W.eng AS eng, " +
                    "SUM(S.ok) AS ok, " +
                    "SUM(S.err) AS err " +
                    "FROM words AS W " +
                    "LEFT JOIN stats AS S " +
                    "ON W.id = S.id_word " +
                    "GROUP BY W.id,W.rus,W.eng " +
                    "HAVING SUM(S.ok)<=5", null)
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
                res.add(WordData(cursor_get(c, "rus"), cursor_get(c, "eng"),0,0,0))
            } catch (e: Exception){
                Log.println(Log.ERROR,"getVariation",e.toString())
                res.add(WordData("", "",0,0,0))
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
        db.execSQL("DELETE FROM stats")
        Toast.makeText(context, "all delete", Toast.LENGTH_SHORT).show()
    }

    // ans: 0-err 1-ok
    fun writeStats(word:WordData, ans:Int){
        val db = this.writableDatabase
        if (ans==0) {
            var values = ContentValues()
            values.put("id_word", word.id)
            values.put("err", 1)
            db.insert("stats", null, values)

            values = ContentValues()
            values.put("id_word", word.id)
            values.put("ok", -1)
            db.insert("stats", null, values)
        }
        else {
            var values = ContentValues()
            values.put("id_word", word.id)
            values.put("ok", 1)
            db.insert("stats", null, values)
        }


        db.close()
    }


}