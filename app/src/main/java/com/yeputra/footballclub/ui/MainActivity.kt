package com.yeputra.footballclub.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.yeputra.footballclub.R
import com.yeputra.footballclub.base.BaseToolbarActivity
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.ui.main_fragment.FavoriteFm
import com.yeputra.footballclub.ui.main_fragment.LastMatchFm
import com.yeputra.footballclub.ui.main_fragment.NextMatchFm
import com.yeputra.footballclub.ui.search.SearchActivity
import com.yeputra.footballclub.utils.changeTo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*

class MainActivity : BaseToolbarActivity<LeaguePresenter>() {
    override fun setButtonBack(): Boolean = false

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun setToolbar(): Toolbar? = toolbar

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_search -> startActivity(Intent(this, SearchActivity::class.java))
        }
        return true
    }
}
