package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addonClick(view: View) {
        val dbHlp = dbHelper(this, null)
        val word = WordData(rus.text.toString(), eng.text.toString())
        dbHlp.addWord(word)
    }

    fun onShowList(view: View) {
        val list = Intent(this, listWords::class.java )
        startActivity(list)
    }

    fun onClickService(view: View) {
        val act = Intent(this, service::class.java )
        startActivity(act)
    }

}


