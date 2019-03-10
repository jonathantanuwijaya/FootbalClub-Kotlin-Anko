package com.yeputra.footballclub.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.ItemDetailAdapter
import com.yeputra.footballclub.base.BaseActivity
import com.yeputra.footballclub.model.Event
import com.yeputra.footballclub.model.Events
import com.yeputra.footballclub.model.Item
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.utils.INTENT_DATA
import kotlinx.android.synthetic.main.activity_detail_match.*

class DetailMatchActivity : BaseActivity<LeaguePresenter>() {

    private lateinit var itemAdapter: ItemDetailAdapter

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        itemAdapter = ItemDetailAdapter(mutableListOf())
        rv_item_detail.layoutManager = LinearLayoutManager(this)
        rv_item_detail.overScrollMode = View.OVER_SCROLL_NEVER
        rv_item_detail.setHasFixedSize(false)
        rv_item_detail.adapter = itemAdapter


        val eventId = intent.extras?.getString(INTENT_DATA)
        presenter.getDetail(eventId?:"0")
    }

    override fun onPresenterSuccess(data: Any?) {
        super.onPresenterSuccess(data)
        when(data){
            is Events -> {
                data.events?.get(0)?.let {
                    setupContentView(it)
                }?: run{
                    onPresenterFailed(getString(R.string.no_internet_connection))
                    finish()
                }
            }
        }
    }

    private fun setupContentView(data: Event){
        tv_date_match.text = data.getFormatDateEvent()
        tv_home_name.text = data.homeTeam
        tv_away_club_name.text = data.awayTeam
        tv_home_score.text = data.homeScore
        tv_away_score.text = data.awayScore

        data.tumb?.let {
            Glide.with(this).load(it).into(img_thumb)
        }

        itemAdapter.addItem(Item("Goals", data.homeGoalDetail, data.awayGoalDetail))
        itemAdapter.addItem(Item("Substitutes", data.homeLineupSubstitutes, data.awayLineupSubstitutes))
        itemAdapter.addItem(Item("Goal Keeper", data.homeLineupGoalkeeper, data.awayLineupGoalkeeper))
        itemAdapter.addItem(Item("Defense", data.homeLineupDefense, data.awayLineupDefense))
        itemAdapter.addItem(Item("Forward", data.homeLineupForward, data.awayLineupForward))
        itemAdapter.addItem(Item("Midfield", data.homeLineupMidfield, data.awayLineupMidfield))
        itemAdapter.addItem(Item("Yellow Card", data.homeYellowCards, data.awayYellowCards))
        itemAdapter.addItem(Item("Red Card", data.homeRedCards, data.awayRedCards))
    }
}
