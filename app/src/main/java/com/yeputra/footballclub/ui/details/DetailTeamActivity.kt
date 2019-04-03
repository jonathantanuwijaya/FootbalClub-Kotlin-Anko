package com.yeputra.footballclub.ui.details

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.VPMatchAdapter
import com.yeputra.footballclub.base.BaseToolbarActivity
import com.yeputra.footballclub.model.Pagers
import com.yeputra.footballclub.model.Team
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.ui.team.PlayersFm
import com.yeputra.footballclub.ui.team.TeamInfoFm
import com.yeputra.footballclub.utils.INTENT_DATA
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : BaseToolbarActivity<LeaguePresenter>() {
    private lateinit var team: Team
    private val bundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        initData()
        initViewConfigure()
    }

    fun initData(){
        team = intent.getParcelableExtra(INTENT_DATA)
        bundle.putParcelable(INTENT_DATA, team)
    }

    fun initViewConfigure(){
        Glide.with(this)
            .load(team.logo)
            .apply(RequestOptions().placeholder(R.drawable.ic_logo_default))
            .into(img_logo)
        tv_title.text = team.name?.toUpperCase()
        tv_website.text = team.website

        val teamInfoFm = TeamInfoFm()
        val playersFm = PlayersFm()
        teamInfoFm.arguments = bundle
        playersFm.arguments = bundle

        val fragments = mutableListOf(
            Pagers(getString(R.string.lbl_team_info), teamInfoFm),
            Pagers(getString(R.string.lbl_players), playersFm)
        )
        viewpager.adapter = supportFragmentManager?.let { VPMatchAdapter(fragments, it) }
        tab.setupWithViewPager(viewpager)
        viewpager.overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun setButtonBack(): Boolean = true

    override fun setToolbar(): Toolbar? = toolbar

}
