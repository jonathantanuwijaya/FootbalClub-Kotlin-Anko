package com.yeputra.footballclub.ui

import android.os.Bundle
import android.view.View
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.ViewPagerAdapter
import com.yeputra.footballclub.base.BaseActivity
import com.yeputra.footballclub.presenter.LeaguePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_with_tab.*

class MainActivity : BaseActivity<LeaguePresenter>() {

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager.adapter = ViewPagerAdapter(supportFragmentManager)
        tab.setupWithViewPager(viewpager)
        viewpager.overScrollMode = View.OVER_SCROLL_NEVER
    }
}
