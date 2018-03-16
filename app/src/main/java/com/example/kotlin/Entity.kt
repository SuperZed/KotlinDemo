package com.example.kotlin

/**
 * Created by Administrator on 2018/3/15.
 */

data class Category(
        val count: Int,
        val error: Boolean,
        val results: List<Article>
)

data class Article(
        val desc: String,
        val ganhuo_id: String,
        val publishedAt: String,
        val readability: String,
        val type: String,
        val url: String,
        val who: String
)

data class Date(
        val error: Boolean,
        val results: List<String>
)

data class GankModel(
        val category: List<String>,
        val error: Boolean,
        val results: Map<String, List<Gank>>
)

data class Gank(
        val _id: String,
        val createdAt: String,
        val desc: String,
        val images: MutableList<String> = mutableListOf<String>(),
        val publishedAt: String,
        val source: String,
        val type: String,
        val url: String,
        val used: Boolean,
        val who: String,
        val isHeader: Boolean = false
) {
    constructor(desc: String, isHeader: Boolean) : this("", "", desc, ArrayList<String>(), "", "", "", "", false, "", isHeader)
}

