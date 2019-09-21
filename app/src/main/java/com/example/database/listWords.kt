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

        val dbHandler = dbHelper(this, null)
        val cursor = dbHandler.getAllWords()
        cursor!!.moveToFirst()
        words.add(WordData(cursor_get(cursor,"rus"), cursor_get(cursor,"eng")))
        while (cursor.moveToNext()) {
            words.add(WordData(cursor.getString(cursor.getColumnIndex("rus")), cursor.getString(cursor.getColumnIndex("eng"))))
        }
        cursor.close()
        list.adapter = adapterForView(words, this)
    }

    fun cursor_get (cursor:Cursor?, Name:String):String{
       return cursor?.getString(cursor.getColumnIndex("rus")) ?: ""
    }
}
