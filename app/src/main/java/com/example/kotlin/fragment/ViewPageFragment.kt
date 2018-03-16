package com.example.kotlin.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.kotlin.Article
import com.example.kotlin.R
import com.example.kotlin.activity.WebViewActivity
import com.example.kotlin.adapter.ArticleAdapter
import com.example.kotlindemo.net.IService
import com.example.kotlindemo.net.NetClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_viewpage_item.*

/**
 * Created by Acer on 2018/3/14.
 */
class ViewPageFragment : BaseFragment(), BaseQuickAdapter.RequestLoadMoreListener {


    lateinit var title: String
    private var mArticleList = mutableListOf<Article>()
    private lateinit var mAdapter: ArticleAdapter
    var page: Int = 1

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_viewpage_item, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        title = arguments.getString("title")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        refreshLayout.setOnRefreshListener {
            page = 1
            load(page)
        }
        mAdapter = ArticleAdapter(mArticleList)
        mAdapter.setOnLoadMoreListener(this, recyclerView)
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra("url", mArticleList[position].url)
            intent.putExtra("title", mArticleList[position].desc)
            activity.startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mAdapter
        load(1)

    }

    override fun onFragmentVisibleChange(isVisible: Boolean) {
        super.onFragmentVisibleChange(isVisible)
    }

    fun load(page: Int) {
        NetClient.create(IService::class.java)
                .getCategory(title, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (!it.error) {
                        refreshLayout.isRefreshing = false
                        mAdapter.loadMoreComplete()
                        if (page == 1) {
                            mArticleList.clear()
                        }
                        var data = it.results
                        if (data.isNotEmpty()) {
                            mArticleList.addAll(data)
                            mAdapter.notifyDataSetChanged()
                        } else {

                        }
                    }
                }, {
                    Log.e("xie", it.message.toString() + "")
                })
    }

    override fun onLoadMoreRequested() {
        page++
        load(page)
    }

}