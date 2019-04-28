package com.yeputra.footballclub.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.yeputra.footballclub.R
import com.yeputra.footballclub.base.BaseToolbarActivity
import com.yeputra.footballclub.base.ITabView
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.ui.dashboard.*
import com.yeputra.footballclub.ui.search.SearchMatchActivity
import com.yeputra.footballclub.ui.search.SearchTeamActivity
import com.yeputra.footballclub.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_tab.*

class MainActivity : BaseToolbarActivity<LeaguePresenter>(), ITabView {
    private var fragmentActived: Fragment? = null

    override fun setButtonBack(): Boolean = true

    override fun setToolbar(): Toolbar? = toolbar

    override fun getTabLayout(): TabLayout? = tab

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar_title.text = league.name

        val home = HomeFm()
        val standing = StandingFm()
        val match = MatchFm()
        val team = TeamFm()
        val favorite = FavoritesFm()

        fragmentAdd(R.id.main_container, home)
        fragmentAdd(R.id.main_container, standing)
        fragmentAdd(R.id.main_container, match)
        fragmentAdd(R.id.main_container, team)
        fragmentAdd(R.id.main_container, favorite)

        button_navigation.setOnNavigationItemSelectedListener {
            fragmentActived?.let { it1 -> fragmentHide(it1) }

            when(it.itemId) {
                R.id.menu_home -> {
                    tab.gone()
                    fragmentActived = home
                }
                R.id.menu_standing -> {
                    tab.gone()
                    fragmentActived = standing
                }
                R.id.menu_match -> {
                    tab.visible()
                    fragmentActived = match
                }
                R.id.menu_teams -> {
                    tab.gone()
                    fragmentActived = team
                }
                R.id.menu_favorites -> {
                    tab.visible()
                    fragmentActived = favorite
                }
            }
            fragmentActived?.let { it1 -> fragmentShow(it1) }
            true
        }
        button_navigation.selectedItemId = R.id.menu_home
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_search -> {
                when (fragmentActived) {
                    is MatchFm -> {
                        startActivity(Intent(this, SearchMatchActivity::class.java))
                    }
                    is TeamFm -> {
                        startActivity(Intent(this, SearchTeamActivity::class.java))
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
