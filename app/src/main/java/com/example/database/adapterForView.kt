package com.example.database

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_word.view.*


class adapterForView(val items : ArrayList<WordData>, val context: Context) : RecyclerView.Adapter<adapterForView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_word, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rus.text = items.get(position).rus
        holder.eng.text = items.get(position).eng
        val dbHlp = dbHelper(context, null)
        val variation = dbHlp.getVariation()
        holder.var1.text = variation[0].eng
        holder.var2.text = variation[1].eng
        holder.var3.text = variation[2].eng
        holder.var4.text = variation[3].eng
//        holder.eng.setOnClickListener {
//            holder.element.text="123"
//        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val rus = view.rus
        val eng = view.eng
        val var1 = view.var1
        val var2 = view.var2
        val var3 = view.var3
        val var4 = view.var4

    }
}