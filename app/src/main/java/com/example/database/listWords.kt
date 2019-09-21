package com.example.database

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list_words.*

class listWords : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_words)

        list.layoutManager = LinearLayoutManager(this)

        var words : ArrayList<WordData> = ArrayList()

        val dbHnd = dbHelper(this, null)
        val cursor = dbHnd.getAllWords()
        cursor!!.moveToFirst()
        words.add(
                WordData(dbHnd.cursor_get(cursor,"rus"),
                    dbHnd.cursor_get(cursor,"eng"),
                    forInt(dbHnd.cursor_get(cursor,"id")),
                    forInt(dbHnd.cursor_get(cursor,"ok")),
                        forInt(dbHnd.cursor_get(cursor,"err"))
                )
        )
        while (cursor.moveToNext()) {
            words.add(
                WordData(dbHnd.cursor_get(cursor,"rus"),
                    dbHnd.cursor_get(cursor,"eng"),
                    forInt(dbHnd.cursor_get(cursor,"id")),
                    forInt(dbHnd.cursor_get(cursor,"ok")),
                    forInt(dbHnd.cursor_get(cursor,"err"))
                )
            )
        }
        cursor.close()
        list.adapter = adapterForView(words, this)
    }

    fun forInt(s:String):Int{
        if (s.isEmpty())
            return 0
           else
            return s.toInt()

    }


}
