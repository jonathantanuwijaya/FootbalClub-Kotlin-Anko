package com.yeputra.footballclub.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.TeamAdapter
import com.yeputra.footballclub.base.BaseFragment
import com.yeputra.footballclub.model.TeamsResponse
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.ui.details.DetailTeamActivity
import com.yeputra.footballclub.utils.INTENT_DATA
import com.yeputra.footballclub.utils.league
import kotlinx.android.synthetic.main.list_match.*

class TeamFm : BaseFragment<LeaguePresenter>() {
    private lateinit var teamAdapter: TeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? = inflater.inflate(R.layout.list_match,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initViewConfigure()
    }

    private fun initData() {
        teamAdapter = TeamAdapter(mutableListOf()){
            startActivity(
                Intent(context,DetailTeamActivity::class.java)
                    .putExtra(INTENT_DATA, it))
        }
        presenter.getTeams(league.idLeague.toString())
    }

    private fun initViewConfigure() {
        rv_match.layoutManager = GridLayoutManager(context, 2)
        rv_match.adapter = teamAdapter

        swipe_container.setColorSchemeColors(
            ContextCompat.getColor(getContextView(),R.color.colorPrimary),
            ContextCompat.getColor(getContextView(),R.color.colorPrimaryDark),
            ContextCompat.getColor(getContextView(),R.color.colorAccent),
            ContextCompat.getColor(getContextView(),R.color.devider)
        )

        swipe_container.setOnRefreshListener {
            presenter.getTeams(league.idLeague.toString())
        }
    }

    override fun onPresenterSuccess(data: Any?) {
        super.onPresenterSuccess(data)
        when(data){
            is TeamsResponse -> data.teams?.let { teamAdapter.replaceItem(it) }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        menu?.findItem(R.id.menu_search)?.isVisible = true
        super.onPrepareOptionsMenu(menu)
    }

    override fun showProgressbar() {
        swipe_container.isRefreshing = true
    }

    override fun hideProgressbar() {
        swipe_container.isRefreshing = false
    }

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

}
