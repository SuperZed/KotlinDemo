package com.example.kotlindemo.net

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Administrator on 2018/3/8.
 */
object NetClient {

    private var mBuilder: OkHttpClient.Builder? = null

    init {
        init()
    }

    /**
     * 创建相应的服务接口
     */
    fun <T> create(service: Class<T>): T {
        return Retrofit.Builder()
                .baseUrl("http://gank.io")
                .client(mBuilder!!.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(service)
    }

    private fun init() {
        mBuilder = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
        //添加日志拦截器
        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        mBuilder!!.addInterceptor(interceptor)
    }


}