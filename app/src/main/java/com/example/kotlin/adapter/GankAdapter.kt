package com.example.kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kotlin.Gank
import com.example.kotlin.R

/**
 * Created by Administrator on 2018/3/15.
 */
class GankAdapter(var context: Context, val ganks: List<Gank>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val HEADER = 0
    private val NORMAL = 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (getItemViewType(position) == HEADER) {
            val headerView = holder as HeadView
            headerView.setData(ganks[position])
        } else {
            val itemView = holder as ItemView
            itemView.setData(ganks[position])
        }
    }

    override fun getItemCount(): Int {
        return ganks.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (getItemViewType(viewType) == HEADER) {
            return HeadView(LayoutInflater.from(context).inflate(R.layout.layout_gank_header, parent, false))
        } else {
            return ItemView(LayoutInflater.from(context).inflate(R.layout.layout_gank_item, parent, false))
        }
    }

    class HeadView(view: View) : RecyclerView.ViewHolder(view) {
        var textview = view.findViewById<TextView>(R.id.header_title)

        fun setData(gank: Gank) {
            textview.text = gank.desc
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (ganks[position].isHeader) {
            HEADER
        } else {
            NORMAL
        }
    }

    class ItemView(view: View) : RecyclerView.ViewHolder(view) {
        var textview = view.findViewById<TextView>(R.id.item_title)
        var image = view.findViewById<ImageView>(R.id.image)
        fun setData(gank: Gank) {
            textview.text = gank.desc
            if (gank.images != null && gank.images.isNotEmpty()) {
                Glide.with(itemView.context).load(gank.images[0] + "?imageInfo").into(image)
            } else {
                image.visibility = View.GONE
            }
        }
    }

}