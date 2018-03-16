package com.example.kotlin.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

/**
 * Created by Administrator on 2018/3/15.
 */
class ProgressView : View {

    private lateinit var mPaint: Paint
    private var mWidth: Float = 0f
    private var mHeight: Float = 0f
    private var progress: Int = 0//加载进度

    constructor(mContext: Context) : super(mContext) {
        val context = mContext
        init()
    }

    private fun init() {
        mPaint = Paint()
        mPaint.isDither = true
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = 10f
        mPaint.color = Color.RED
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mWidth = w.toFloat()
        mHeight = h.toFloat()
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas!!.drawRect(0f, 0f, mWidth * progress / 100, mHeight, mPaint);
        super.onDraw(canvas)
    }

    /**
     * 设置新进度 重新绘制
     *
     * @param newProgress 新进度
     */
    fun setProgress(newProgress: Int) {
        this.progress = newProgress
        invalidate()
    }

    /**
     * 设置进度条颜色
     *
     * @param color 色值
     */
    fun setColor(color: Int) {
        mPaint.setColor(color)
    }


}