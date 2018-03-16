package com.example.kotlin.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlin.R
import com.example.kotlin.Article

/**
 * Created by Administrator on 2018/3/15.
 */
class ArticleAdapter(var list: List<Article>) : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.layout_article_item, list) {
    override fun convert(helper: BaseViewHolder?, item: Article?) {
        helper!!.getView<TextView>(R.id.title).text = item!!.desc
        helper!!.getView<TextView>(R.id.author).text = item!!.who
    }

}