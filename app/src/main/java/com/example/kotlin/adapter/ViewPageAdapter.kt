package com.example.kotlin.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

/**
 * Created by Acer on 2018/3/14.
 */
class ViewPageAdapter(var fragments: List<Fragment>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {

    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragments.get(position).arguments.getString("title")
    }
}