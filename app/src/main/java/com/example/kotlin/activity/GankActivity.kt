package com.example.kotlin.activity

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.kotlin.Gank
import com.example.kotlin.R
import com.example.kotlin.adapter.GankAdapter
import com.example.kotlindemo.net.IService
import com.example.kotlindemo.net.NetClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_gank.*

/**
 * Created by Administrator on 2018/3/15.
 */
class GankActivity : BaseActivity() {

    var ganks = mutableListOf<Gank>()
    lateinit var mAdapter: GankAdapter

    override fun getLayoutRes(): Int {
        return R.layout.activity_gank
    }

    override fun initView() {
        mAdapter= GankAdapter(this,ganks)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=mAdapter
        getGank(intent.getStringExtra("date"))
    }

    fun getGank(data: String) {
        NetClient.create(IService::class.java)
                .getGank(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (!it.error) {
                        var data = it.results
                        val c = it.category
                        c.forEach {
                            ganks.add(Gank(it,true))
                            ganks.addAll(data.get(it)!!)
                        }
                        mAdapter.notifyDataSetChanged()
                    }
                }, {
                    Log.e("xie", it.message.toString() + "")
                })
    }
}