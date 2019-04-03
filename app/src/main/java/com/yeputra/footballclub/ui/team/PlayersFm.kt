package com.yeputra.footballclub.ui.team

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.PlayersAdapter
import com.yeputra.footballclub.base.BaseFragment
import com.yeputra.footballclub.model.PlayersResponse
import com.yeputra.footballclub.model.Team
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.utils.INTENT_DATA
import com.yeputra.footballclub.utils.league
import kotlinx.android.synthetic.main.fragment_players.*

class PlayersFm : BaseFragment<LeaguePresenter>() {
    private var btSheetFrag = PlayerDetailFm()
    private lateinit var playersAdapter: PlayersAdapter
    private lateinit var team: Team


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? = inflater.inflate(R.layout.fragment_players,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initViewConfigure()
    }

    private fun initData() {
        arguments?.getParcelable<Team>(INTENT_DATA)?.let {
            team = it
        }
        playersAdapter = PlayersAdapter(mutableListOf()){
            val bundle = Bundle()
            bundle.putParcelable(INTENT_DATA, it)
            btSheetFrag.arguments = bundle
            btSheetFrag.show(fragmentManager, "")
        }
        team.idTeam?.let { presenter.getPlayers(it) }
    }

    private fun initViewConfigure() {
        rv_match.layoutManager = GridLayoutManager(context, 2)
        rv_match.adapter = playersAdapter

        swipe_container.setColorSchemeColors(
            ContextCompat.getColor(getContextView(),R.color.colorPrimary),
            ContextCompat.getColor(getContextView(),R.color.colorPrimaryDark),
            ContextCompat.getColor(getContextView(),R.color.colorAccent),
            ContextCompat.getColor(getContextView(),R.color.devider)
        )

        swipe_container.setOnRefreshListener {
            presenter.getTeams(league)
        }
    }

    override fun onPresenterSuccess(data: Any?) {
        super.onPresenterSuccess(data)
        when(data){
            is PlayersResponse -> data.player?.let { playersAdapter.replaceItem(it) }
        }
    }

    override fun showProgressbar() {
        swipe_container.isRefreshing = true
    }

    override fun hideProgressbar() {
        swipe_container.isRefreshing = false
    }

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)
}
