package com.example.database

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.lang.Exception
import java.net.URL
import java.nio.charset.Charset


class service : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
    }

    fun onClickDelAll(view: View) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Удалить все слова?")
        builder.setNegativeButton(android.R.string.no) { dialog, which -> }
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            val dbHlp = dbHelper(this, null)
            dbHlp.allDel()
        }
        builder.show()
    }

    fun testContent(view: View) {

        var ret : Boolean = false

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Добавить тестовый набор слов?")
        builder.setNegativeButton(android.R.string.no) { dialog, which -> ret=true}
        builder.setPositiveButton(android.R.string.yes) { dialog, which -> addTestContent()}
        builder.show()
        if (ret)
            return
    }

    fun addTestContent(){

        Toast.makeText(this, "Добавление....", Toast.LENGTH_LONG)

        val dbHlp = dbHelper(this, null)
        val list = ArrayList<WordData>()

        list.add(WordData("определенный артикль","the [ðə:]"))
        list.add(WordData("и; а, но","and [ænd]"))
        list.add(WordData("неопределенный артикль","a [ə]"))

        for (w in list)
            dbHlp.addWord(w)
    }

    var FILE_SELECT_CODE : Int = 0;
    fun loadFromFile() {
        var intent = Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult( Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (ex : Exception) {
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            val dbHelp = dbHelper(this, null)
            var added:Int=0
            for (line in File(data?.data?.path?.split(":")?.get(1)).readLines()) {
                val w = line.split(";")
                if (w.size<2) continue
                val r = dbHelp.addWord(WordData(w[1],w[0]))
                if (r==dbHelper.ADD_STATUS.ADDED)
                    added++
            }
            Toast.makeText(this, "Добалено $added слов", Toast.LENGTH_SHORT)
        } catch (e : Exception){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT)
        }
    }

    fun setupPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    1)
            }
        } else {
        }
    }

    fun onClickloadFromFile(view: View) {
        setupPermissions()
        loadFromFile()

    }

    fun onClickDelStats(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Удалить всю статистику?")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            val dbHlp = dbHelper(this, null)
            dbHlp.delStats()
        }
        builder.setNegativeButton(android.R.string.no) { dialog, which ->}
        builder.show()
    }

    data class res_add(var context:Context, var added:Int=0, var comment: String="")

    fun onClickLoadFromHTTP(view:View){
        doAsync {
            try {
                val str = URL("http","192.168.0.108",8080,"words").readText(Charset.forName("UTF-8"))
                val dbHelp = dbHelper(this, null)
                for (line in str.lines()) {
                    val w = line.split(";")
                    if (w.size<2) continue
                    val r = dbHelp.addWord(WordData(w[1],w[0]))
                    if (r==dbHelper.ADD_STATUS.ADDED)
                        it.added++
                }
            } catch (e:Exception){
                it.comment+=e.toString()+"\n"
                return@doAsync it
            }
            return@doAsync it
        }.execute(res_add(this))
    }

    class doAsync(val handler: (res_add) -> res_add) : AsyncTask<res_add, Void, res_add>() {
        override fun doInBackground(vararg params: res_add): res_add {
            return handler(params.get(0))
        }

        override fun onPostExecute(res_add: res_add) {
            super.onPostExecute(res_add)
            Toast.makeText(res_add.context, "Добавлено: ${res_add.added}\n${res_add.comment}", Toast.LENGTH_LONG).show()
        }
    }
}
