package com.yeputra.footballclub.ui

import android.os.Bundle
import com.yeputra.footballclub.R
import com.yeputra.footballclub.base.BaseActivity
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.utils.changeTo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<LeaguePresenter>() {

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_prev_match -> {
                    changeTo(R.id.main_container, LastMatchFm())
                }
                R.id.menu_next_match -> {
                    changeTo(R.id.main_container, NextMatchFm())
                }
                R.id.menu_favorites -> {
                    changeTo(R.id.main_container, FavoriteFm())
                }
            }
            true
        }
        button_navigation.selectedItemId = R.id.menu_prev_match
    }
}
