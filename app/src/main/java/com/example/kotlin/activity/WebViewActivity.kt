package com.example.kotlin.activity

import com.example.kotlin.R
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * Created by Administrator on 2018/3/15.
 */
class WebViewActivity : BaseActivity() {

    private lateinit var url: String
    private lateinit var title: String

    override fun getLayoutRes(): Int {
        return R.layout.activity_webview
    }

    override fun initView() {
        url = intent.getStringExtra("url")
        title = intent.getStringExtra("title")
        setTtitleName(title)
        webView.loadUrl(url)
    }

}