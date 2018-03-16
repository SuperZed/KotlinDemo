package com.example.kotlin.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView


/**
 * Created by Administrator on 2018/3/15.
 */
class ProgressWebView : WebView {

    private lateinit var progressView: ProgressView

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        //初始化进度条
        progressView = ProgressView(context)
        progressView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(context, 3f))
        progressView.setColor(Color.parseColor("#2FF0E3"))
        progressView.setProgress(10)
        //把进度条加到Webview中
        addView(progressView)
        //初始化设置
        initWebSettings()
        webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress == 100) {
                    //加载完毕进度条消失
                    progressView.setVisibility(View.GONE)
                } else {
                    //更新进度
                    progressView.setProgress(newProgress)
                }
            }
        }
    }

    private fun initWebSettings() {
        val settings = settings
        //默认是false 设置true允许和js交互
        settings.javaScriptEnabled = true
        //开启 DOM storage API 功能 较大存储空间，使用简单
        settings.domStorageEnabled = true
        //开启 Application Caches 功能 方便构建离线APP 不推荐使用
        settings.setAppCacheEnabled(true)
        val cachePath = context.applicationContext.getDir("cache", Context.MODE_PRIVATE).path
        settings.setAppCachePath(cachePath)
        settings.setAppCacheMaxSize((5 * 1024 * 1024).toLong())
        //设置数据库缓存路径 存储管理复杂数据 方便对数据进行增加、删除、修改、查询 不推荐使用
        settings.databaseEnabled = true
        val dbPath = context.applicationContext.getDir("db", Context.MODE_PRIVATE).path
        settings.databasePath = dbPath
    }

    /**
     * dp转换成px
     *
     * @param context Context
     * @param dp      dp
     * @return px值
     */
    private fun dp2px(context: Context, dp: Float): Int {
        var scale = context.getResources().getDisplayMetrics().density
        return (dp * scale + 0.5f).toInt()
    }

}