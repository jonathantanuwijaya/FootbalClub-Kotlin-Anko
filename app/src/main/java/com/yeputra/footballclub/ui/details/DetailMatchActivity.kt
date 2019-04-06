package com.yeputra.footballclub.ui.details

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.MatchDetailAdapter
import com.yeputra.footballclub.base.BaseActivity
import com.yeputra.footballclub.model.*
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.presenter.MatchFavoritePresenter
import com.yeputra.footballclub.utils.INTENT_DATA
import com.yeputra.footballclub.utils.snackbar
import kotlinx.android.synthetic.main.activity_detail_match.*

class DetailMatchActivity : BaseActivity<LeaguePresenter>(), View.OnClickListener {
    private lateinit var itemAdapter: MatchDetailAdapter
    private lateinit var favPresenter: MatchFavoritePresenter
    private var event: Event? = null
    private var isFavorite: Boolean = false
    private var homeName = ""
    private var eventId = ""

    override fun initPresenter(): LeaguePresenter {
        favPresenter = MatchFavoritePresenter(this)
        return LeaguePresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        initData()
        initViewConfigure()
    }

    private fun initData() {
        eventId = intent.extras?.getString(INTENT_DATA).toString()
        presenter.getDetail(eventId)
    }

    private fun initViewConfigure() {
        itemAdapter = MatchDetailAdapter(mutableListOf())
        rv_item_detail.layoutManager = LinearLayoutManager(this)
        rv_item_detail.overScrollMode = View.OVER_SCROLL_NEVER
        rv_item_detail.setHasFixedSize(false)
        rv_item_detail.adapter = itemAdapter

        swiperefresh.setColorSchemeColors(
            ContextCompat.getColor(getContextView(),R.color.colorPrimary),
            ContextCompat.getColor(getContextView(),R.color.colorPrimaryDark),
            ContextCompat.getColor(getContextView(),R.color.colorAccent),
            ContextCompat.getColor(getContextView(),R.color.devider)
        )

        swiperefresh.setOnRefreshListener {
            presenter.getDetail(eventId)
        }
        bt_favorite.setOnClickListener(this)
    }

    override fun onPresenterSuccess(data: Any?) {
        super.onPresenterSuccess(data)
        when(data){
            is EventsResponse -> {
                data.events?.get(0)?.let {
                    this.event = it
                    setupContentView(it)
                    setFlagFavorite()
                }?: run{
                    onPresenterFailed(getString(R.string.no_internet_connection))
                    finish()
                }
            }
            is TeamsResponse -> {
                data.teams?.let {
                    loadLogoTeam(it[0])
                }
            }
        }
    }

    private fun setupContentView(data: Event){
        data.homeTeam?.let {
            homeName = it
            presenter.getTeam(it)
        }
        data.awayTeam?.let { presenter.getTeam(it) }

        tv_date_match.text = data.getFormatDateEvent()
        tv_home_name.text = data.homeTeam
        tv_away_club_name.text = data.awayTeam

        tv_home_score.text = if(data.homeScore.isNullOrEmpty()) "0" else data.homeScore
        tv_away_score.text = if(data.awayScore.isNullOrEmpty()) "0" else data.awayScore


        itemAdapter.addItem(Item("GOALS", data.homeGoalDetail, data.awayGoalDetail))
        itemAdapter.addItem(Item("SUBSTITUTES", data.homeLineupSubstitutes, data.awayLineupSubstitutes))
        itemAdapter.addItem(Item("GOAL KEEPER", data.homeLineupGoalkeeper, data.awayLineupGoalkeeper))
        itemAdapter.addItem(Item("DEFENSE", data.homeLineupDefense, data.awayLineupDefense))
        itemAdapter.addItem(Item("FORWARD", data.homeLineupForward, data.awayLineupForward))
        itemAdapter.addItem(Item("MIDFIELD", data.homeLineupMidfield, data.awayLineupMidfield))
        itemAdapter.addItem(Item("YELLOW CARD", data.homeYellowCards, data.awayYellowCards))
        itemAdapter.addItem(Item("RED CARD", data.homeRedCards, data.awayRedCards))
    }

    private fun setFlagFavorite(){
        event?.idEvent?.let {
            favPresenter.findOne(it)?.let {
                isFavorite = true
                bt_favorite.setImageResource(R.drawable.ic_favorite_selected)
            }?: run {
                isFavorite = false
                bt_favorite.setImageResource(R.drawable.ic_favorite_unselect)
            }
        }
    }

    override fun onClick(v: View) {
        event?.let {
            if(isFavorite)
                favPresenter.delete(it.idEvent.toString()){
                    snackbar(getString(R.string.fav_delete_successfully))
                }
            else {
                favPresenter.add(MatchFavorite(
                    it.idEvent,
                    it.getFormatDateEvent(),
                    it.homeTeam,
                    it.homeScore,
                    it.awayTeam,
                    it.awayScore
                )) {
                    snackbar(getString(R.string.fav_add_successfully))
                }
            }
            setFlagFavorite()
        }
    }

    private fun loadLogoTeam(team: Team){
        team.logo?.let {
            Glide.with(this)
                .load(it)
                .apply(RequestOptions().placeholder(R.drawable.ic_logo_default))
                .into(if(team.name == homeName) img_home_logo else img_away_logo)
        }
    }

    override fun showProgressbar() {
        swiperefresh.isRefreshing = true
    }

    override fun hideProgressbar() {
        swiperefresh.isRefreshing = false
    }
}
