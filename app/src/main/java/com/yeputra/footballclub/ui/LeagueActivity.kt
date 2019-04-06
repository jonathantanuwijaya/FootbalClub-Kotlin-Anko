package com.yeputra.footballclub.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.LeagueAdapter
import com.yeputra.footballclub.base.BaseActivity
import com.yeputra.footballclub.model.League
import com.yeputra.footballclub.model.LeaguesResponse
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.utils.INTENT_DATA
import com.yeputra.footballclub.utils.league
import kotlinx.android.synthetic.main.activity_league.*

class LeagueActivity : BaseActivity<LeaguePresenter>() {
    private lateinit var adapter: LeagueAdapter

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)

        initData()
        initViewConfigure()
    }

    private fun initData() {
        val leagues = intent.getParcelableExtra<LeaguesResponse>(INTENT_DATA)
        adapter = LeagueAdapter(filterSoccer(leagues)){
            league = it
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
    }

    private fun initViewConfigure() {
        rv_league.layoutManager = LinearLayoutManager(this)
        rv_league.adapter = adapter
    }

    private fun filterSoccer(leagues: LeaguesResponse): MutableList<League>{
        val data = mutableListOf<League>()
        leagues.leagues?.let {
            for(league in it){
                if(league.sport?.toLowerCase() == "soccer"){
                    data.add(league)
                }
            }
        }
        return data
    }

    override fun showProgressbar() {}

    override fun hideProgressbar() {}
}
