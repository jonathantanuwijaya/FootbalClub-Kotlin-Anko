package com.yeputra.footballclub.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.*
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.TeamAdapter
import com.yeputra.footballclub.base.BaseToolbarFragment
import com.yeputra.footballclub.model.TeamsResponse
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.ui.details.DetailTeamActivity
import com.yeputra.footballclub.ui.search.SearchTeamActivity
import com.yeputra.footballclub.utils.INTENT_DATA
import com.yeputra.footballclub.utils.league
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.list_match.*

class TeamFm : BaseToolbarFragment<LeaguePresenter>() {
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
        toolbar_title.text = league.name
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

    override fun showProgressbar() {
        swipe_container.isRefreshing = true
    }

    override fun hideProgressbar() {
        swipe_container.isRefreshing = false
    }

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun setToolbar(): Toolbar? = toolbar

    override fun setButtonBack(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_search -> startActivity(Intent(context, SearchTeamActivity::class.java))
        }
        return true
    }
}
