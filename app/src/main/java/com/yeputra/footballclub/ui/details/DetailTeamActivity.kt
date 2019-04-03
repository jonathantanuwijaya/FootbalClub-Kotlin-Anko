package com.yeputra.footballclub.ui.details

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.VPMatchAdapter
import com.yeputra.footballclub.base.BaseToolbarActivity
import com.yeputra.footballclub.model.Pagers
import com.yeputra.footballclub.model.Team
import com.yeputra.footballclub.model.TeamFavorite
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.presenter.TeamFavoritePresenter
import com.yeputra.footballclub.ui.team.PlayersFm
import com.yeputra.footballclub.ui.team.TeamInfoFm
import com.yeputra.footballclub.utils.INTENT_DATA
import com.yeputra.footballclub.utils.snackbar
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : BaseToolbarActivity<LeaguePresenter>() {
    private lateinit var team: Team
    private lateinit var teamFavoritePresenter: TeamFavoritePresenter
    private var isFavorite = false
    private val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        initData()
        initViewConfigure()
    }

    private fun initData(){
        team = intent.getParcelableExtra(INTENT_DATA)
        bundle.putParcelable(INTENT_DATA, team)
        teamFavoritePresenter = TeamFavoritePresenter(this)
        setFlagFavorite()
    }

    private fun initViewConfigure(){
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

    private fun setFlagFavorite(){
        team.idTeam?.let {
            teamFavoritePresenter.findOne(it)?.let {
                isFavorite = true
            }?: run {
                isFavorite = false
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val item = menu?.findItem(R.id.menu_favorite)
        item?.let { setMenuIconFavorite(it, isFavorite) }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_favorite -> {
                isFavorite = !isFavorite
                if(!isFavorite){
                    team.idTeam?.let {teamFavoritePresenter.delete(it){
                        snackbar(getString(R.string.fav_team_delete_successfully))
                        setMenuIconFavorite(item, isFavorite)
                    }}
                }else{
                    teamFavoritePresenter.add(
                        TeamFavorite(
                            team.idTeam,
                            team.name,
                            team.logo
                        )){
                        snackbar(getString(R.string.fav_team_add_successfully))
                        setMenuIconFavorite(item, isFavorite)
                    }
                }
            }
        }
        return true
    }

    private fun setMenuIconFavorite(item: MenuItem, isFavorited: Boolean){
        if(isFavorited)
            item.icon = ContextCompat.getDrawable(getContextView(), R.drawable.ic_favorite_selected)
        else
            item.icon = ContextCompat.getDrawable(getContextView(), R.drawable.ic_favorite_unselect)
    }

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun setButtonBack(): Boolean = true

    override fun setToolbar(): Toolbar? = toolbar

}
