package com.example.kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Administrator on 2018/3/15.
 */
class DateAdapter(var list: List<String>, var itemClick: (date: String) -> Unit) : RecyclerView.Adapter<DateAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.textView.text = list[position]
        holder!!.textView.setOnClickListener { itemClick(list[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var textview = TextView(parent!!.context)
        textview.setPadding(15,20,15,20)
        textview.textSize = 20f
        return ViewHolder(textview)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}