package com.example.database

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_word.view.*
import kotlin.math.nextUp
import kotlin.math.round


class adapterForView(val items : ArrayList<WordData>, val context: Context) : RecyclerView.Adapter<adapterForView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_word, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rus.text = "---"
        holder.eng.text = items.get(position).eng
        val dbHlp = dbHelper(context, null)
        val variation = dbHlp.getVariation()
        val true_trans = round(Math.random().nextUp()*3).toInt()
        variation[true_trans]= WordData(items.get(position).rus, holder.eng.text.toString())
        holder.var1.text = variation[0].rus
        holder.var2.text = variation[1].rus
        holder.var3.text = variation[2].rus
        holder.var4.text = variation[3].rus


        holder.var1.setOnClickListener {Check(variation[0].rus, items.get(position).rus, holder)}
        holder.var2.setOnClickListener {Check(variation[1].rus, items.get(position).rus, holder)}
        holder.var3.setOnClickListener {Check(variation[2].rus, items.get(position).rus, holder)}
        holder.var4.setOnClickListener {Check(variation[3].rus, items.get(position).rus, holder)}

    }

    fun Check(s1:String, s2:String, holder:ViewHolder){
        if (s1==s2)
           holder.eng.text="Правильно"
        else
            holder.eng.text="Ошибка"
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