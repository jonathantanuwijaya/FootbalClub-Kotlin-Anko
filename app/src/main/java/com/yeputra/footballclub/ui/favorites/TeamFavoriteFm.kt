package com.yeputra.footballclub.ui.favorites

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.TeamAdapter
import com.yeputra.footballclub.base.BaseFragment
import com.yeputra.footballclub.model.Team
import com.yeputra.footballclub.model.TeamsResponse
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.presenter.TeamFavoritePresenter
import com.yeputra.footballclub.ui.details.DetailTeamActivity
import com.yeputra.footballclub.utils.INTENT_DATA
import com.yeputra.footballclub.utils.gone
import kotlinx.android.synthetic.main.list_match.*

class TeamFavoriteFm : BaseFragment<TeamFavoritePresenter>() {
    private lateinit var teamAdapter: TeamAdapter
    private lateinit var presenterApi: LeaguePresenter

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
        presenterApi = LeaguePresenter(this)
        teamAdapter = TeamAdapter(mutableListOf()){
            showProgressbar()
            it.name?.let { it1 -> presenterApi.getTeam(it1) }
        }
        getRepository()
    }

    override fun onResume() {
        super.onResume()
        getRepository()
    }

    private fun getRepository(){
        presenter.findAll {
            val teams = mutableListOf<Team>()
            for(data in it){
                teams.add(
                    Team(
                        data.teamId,
                        data.teamName,"",
                        data.teamLogo,"",""
                    )
                )
            }
            teamAdapter.replaceItem(teams)
        }
    }

    private fun initViewConfigure() {
        header.gone()
        rv_match.layoutManager = GridLayoutManager(context, 2)
        rv_match.adapter = teamAdapter

        swipe_container.setColorSchemeColors(
            ContextCompat.getColor(getContextView(),R.color.colorPrimary),
            ContextCompat.getColor(getContextView(),R.color.colorPrimaryDark),
            ContextCompat.getColor(getContextView(),R.color.colorAccent),
            ContextCompat.getColor(getContextView(),R.color.devider)
        )

        swipe_container.setOnRefreshListener {
            getRepository()
        }
    }

    override fun onPresenterSuccess(data: Any?) {
        super.onPresenterSuccess(data)
        when(data){
            is TeamsResponse -> {
                hideProgressbar()
                startActivity(
                    Intent(context, DetailTeamActivity::class.java)
                        .putExtra(INTENT_DATA, data.teams?.get(0)))
            }
        }
    }

    override fun showProgressbar() {
        swipe_container.isRefreshing = true
    }

    override fun hideProgressbar() {
        swipe_container.isRefreshing = false
    }

    override fun initPresenter(): TeamFavoritePresenter = TeamFavoritePresenter(this)
}
