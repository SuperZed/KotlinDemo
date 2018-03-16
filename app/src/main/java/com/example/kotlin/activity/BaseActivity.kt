package com.example.kotlin.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kotlin.R
import com.example.kotlin.view.TitleBar


/**
 * Created by Administrator on 2018/3/15.
 */
abstract class BaseActivity : AppCompatActivity() {
    lateinit var titleBar: TitleBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        titleBar = findViewById(R.id.titleBar)
        titleBar.setTitleSize(18f)
        titleBar.setLeftImageResource(R.drawable.ic_chevron_left_white_30dp)
        titleBar.setTitleColor(Color.WHITE)
        titleBar.setDividerColor(Color.parseColor("#dddddd"))
        titleBar.setLeftClickListener {
            finish()
        }
        initView()
    }

    abstract fun getLayoutRes(): Int
    abstract fun initView()

    fun setTtitleName(title: String) {
        titleBar.setTitle(title)
    }


}