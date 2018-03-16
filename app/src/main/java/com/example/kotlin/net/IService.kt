package com.example.kotlindemo.net

import com.example.kotlin.Category
import com.example.kotlin.Date
import com.example.kotlin.GankModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Administrator on 2018/3/8.
 */
interface IService {

    @GET("/api/search/query/listview/category/{type}/count/20/page/{page}")
    fun getCategory(@Path("type") type: String, @Path("page") page: Int): Observable<Category>

    @GET("/api/day/history")
    fun getDate(): Observable<Date>

    @GET("/api/day/{date}")
    fun getGank(@Path("date") date: String): Observable<GankModel>
}