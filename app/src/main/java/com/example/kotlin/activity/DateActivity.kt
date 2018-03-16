package com.example.kotlin.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.kotlin.R
import com.example.kotlin.adapter.DateAdapter
import com.example.kotlin.view.RecycleViewDivider
import com.example.kotlindemo.net.IService
import com.example.kotlindemo.net.NetClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_date.*
import org.jetbrains.anko.toast


/**
 * Created by Administrator on 2018/3/15.
 */
class DateActivity : BaseActivity() {

    private lateinit var mAdapter: DateAdapter
    private var list = mutableListOf<String>()

    override fun getLayoutRes(): Int {
        return R.layout.activity_date
    }

    override fun initView() {
        setTtitleName("选择日期")
        mAdapter = DateAdapter(list) { date ->
            val intent = Intent(this@DateActivity, GankActivity::class.java)
            intent.putExtra("date", date.replace("-", "/"))
            startActivity(intent)

        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(RecycleViewDivider(this@DateActivity, LinearLayoutManager.HORIZONTAL))
        recyclerView.adapter = mAdapter
        load()
    }

    fun load() {
        NetClient.create(IService::class.java)
                .getDate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (!it.error) {
                        var data = it.results
                        list.addAll(data)
                        mAdapter.notifyDataSetChanged()
                    }
                }, {
                    Log.e("xie", it.message.toString() + "")
                })
    }


}