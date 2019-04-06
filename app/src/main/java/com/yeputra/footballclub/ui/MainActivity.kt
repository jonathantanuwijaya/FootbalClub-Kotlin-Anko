package com.yeputra.footballclub.ui

import android.os.Bundle
import com.yeputra.footballclub.R
import com.yeputra.footballclub.base.BaseActivity
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.ui.dashboard.*
import com.yeputra.footballclub.utils.changeTo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<LeaguePresenter>() {

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> {
                    changeTo(R.id.main_container, HomeFm())
                }
                R.id.menu_standing -> {
                    changeTo(R.id.main_container, StandingFm())
                }
                R.id.menu_match -> {
                    changeTo(R.id.main_container, MatchsFm())
                }
                R.id.menu_teams -> {
                    changeTo(R.id.main_container, TeamFm())
                }
                R.id.menu_favorites -> {
                    changeTo(R.id.main_container, FavoritesFm())
                }
            }
            true
        }
        button_navigation.selectedItemId = R.id.menu_home
    }
}
