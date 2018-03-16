package com.example.kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.kotlin.adapter.ViewPageAdapter
import com.example.kotlin.fragment.ViewPageFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import com.example.kotlin.R


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val tabs = listOf<String>("all", "Android", "iOS", "App", "休息视频", "前端", "福利", "拓展资源", "瞎推荐")
    val fragments = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        tabs.forEach {
            var fragment = ViewPageFragment()
            var bundle = Bundle()
            bundle.putString("title", it)
            fragment.arguments = bundle
            fragments.add(fragment)
        }
        val adapter = ViewPageAdapter(fragments, supportFragmentManager)
        viewPage.adapter = adapter
        tabLayout.setupWithViewPager(viewPage)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_relaxed -> {
//                val intent = Intent(this@MainActivity, DateActivity::class.java)
//                startActivity(intent)
            }
            R.id.nav_date->{
                val intent = Intent(this@MainActivity, DateActivity::class.java)
                startActivity(intent)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
