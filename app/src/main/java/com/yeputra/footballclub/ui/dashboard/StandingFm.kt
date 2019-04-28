package com.yeputra.footballclub.ui.dashboard

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.StandingsAdapter
import com.yeputra.footballclub.base.BaseFragment
import com.yeputra.footballclub.model.StandingsResponse
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.utils.league
import kotlinx.android.synthetic.main.fragment_standing.*

class StandingFm : BaseFragment<LeaguePresenter>() {
    private lateinit var adapter: StandingsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        return inflater.inflate(R.layout.fragment_standing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initViewConfigure()
    }

    private fun initData() {
        adapter = StandingsAdapter(mutableListOf()){}
        presenter.getStanding(league.idLeague.toString())
    }

    private fun initViewConfigure(){
        rv_match.layoutManager = LinearLayoutManager(context)
        rv_match.overScrollMode = View.OVER_SCROLL_NEVER
        rv_match.adapter = adapter

        swipe_container.setColorSchemeColors(
            ContextCompat.getColor(getContextView(),R.color.colorPrimary),
            ContextCompat.getColor(getContextView(),R.color.colorPrimaryDark),
            ContextCompat.getColor(getContextView(),R.color.colorAccent),
            ContextCompat.getColor(getContextView(),R.color.devider)
        )

        swipe_container.setOnRefreshListener {
            presenter.getStanding(league.idLeague.toString())
        }
    }

    override fun onPresenterSuccess(data: Any?) {
        super.onPresenterSuccess(data)
        when(data){
            is StandingsResponse ->
                data.table?.let { adapter.replaceItem(it) }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        menu?.findItem(R.id.menu_search)?.isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    override fun showProgressbar() {
        swipe_container?.isRefreshing = true
    }

    override fun hideProgressbar() {
        swipe_container?.isRefreshing = false
    }

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)
}
