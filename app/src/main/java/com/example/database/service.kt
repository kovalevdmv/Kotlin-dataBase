package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class service : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
    }

    fun onClickDelAll(view: View) {
        val dbHlp = dbHelper(this, null)
        dbHlp.allDel()

    }
}
