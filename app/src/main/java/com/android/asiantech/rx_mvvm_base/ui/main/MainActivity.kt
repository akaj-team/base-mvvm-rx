package com.android.asiantech.rx_mvvm_base.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.ui.base.BaseActivity
import com.android.asiantech.rx_mvvm_base.ui.main.favorite.FavoriteFragment
import com.android.asiantech.rx_mvvm_base.ui.main.home.HomeFragment
import com.android.asiantech.rx_mvvm_base.ui.main.setting.SettingFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initSearchQuery()
    }

    private fun initSearchQuery() {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                doMySearch(query)
            }
        }
    }

    private fun doMySearch(query: String) {
        // TODO search query here
    }

    private fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.title_home)

        val adapter = MainViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(HomeFragment(), getString(R.string.title_home))
        adapter.addFrag(FavoriteFragment(), getString(R.string.title_favorite))
        adapter.addFrag(SettingFragment(), getString(R.string.title_setting))
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        val tabIcons = intArrayOf(R.drawable.ic_home, R.drawable.ic_favorite, R.drawable.ic_setting)
        tabLayout.getTabAt(0)?.setIcon(tabIcons[0]);
        tabLayout.getTabAt(1)?.setIcon(tabIcons[1]);
        tabLayout.getTabAt(2)?.setIcon(tabIcons[2]);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.menu_search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }

        return true
    }
}
