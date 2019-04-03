package com.yeputra.footballclub.ui.search

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.jakewharton.rxbinding.widget.RxTextView
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.ListTeamAdapter
import com.yeputra.footballclub.base.BaseToolbarActivity
import com.yeputra.footballclub.model.TeamsResponse
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.ui.details.DetailTeamActivity
import com.yeputra.footballclub.utils.INTENT_DATA
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.app_bar_search.*
import java.util.concurrent.TimeUnit


class SearchTeamActivity : BaseToolbarActivity<LeaguePresenter>() {
    private lateinit var teamAdapter: ListTeamAdapter

    override fun setButtonBack(): Boolean = true

    override fun setToolbar(): Toolbar? = toolbar

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initData()
        initViewConfigure()
    }

    private fun initData(){
        teamAdapter = ListTeamAdapter(mutableListOf()){
            startActivity(
                Intent(this, DetailTeamActivity::class.java)
                    .putExtra(INTENT_DATA, it))
        }
    }

    private fun initViewConfigure(){
        rv_match.layoutManager = GridLayoutManager(this, 2)
        rv_match.adapter = teamAdapter

        et_finder.hint = getString(R.string.find_team)
        RxTextView.textChangeEvents(et_finder)
            .debounce(150, TimeUnit.MILLISECONDS)
            .filter { !it.text().toString().isEmpty()}
            .subscribe {
                presenter.getTeam(it.text().toString())
            }
    }

    override fun onPresenterSuccess(data: Any?) {
        super.onPresenterSuccess(data)
        when(data){
            is TeamsResponse -> {
                data.teams?.let { teamAdapter.replaceItem(it) }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return true
    }
}
