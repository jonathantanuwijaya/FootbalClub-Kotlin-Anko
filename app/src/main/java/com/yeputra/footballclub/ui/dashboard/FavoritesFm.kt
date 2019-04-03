package com.yeputra.footballclub.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.VPagerAdapter
import com.yeputra.footballclub.base.BaseFragment
import com.yeputra.footballclub.model.Pagers
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.ui.favorites.MatchFavoriteFm
import com.yeputra.footballclub.ui.favorites.TeamFavoriteFm
import kotlinx.android.synthetic.main.app_bar_tab.*
import kotlinx.android.synthetic.main.fragment_matchs.*

class FavoritesFm : BaseFragment<LeaguePresenter>() {
    override fun showProgressbar() {}

    override fun hideProgressbar() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? = inflater.inflate(R.layout.fragment_matchs,container,false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar_title.text = context?.getString(R.string.botnav_favorite)
        val fragments = mutableListOf(
            Pagers(getContextView().getString(R.string.botnav_match), MatchFavoriteFm()),
            Pagers(getContextView().getString(R.string.lbl_team), TeamFavoriteFm())
        )
        viewpager.adapter = VPagerAdapter(fragments, childFragmentManager)
        tab.setupWithViewPager(viewpager)
        viewpager.overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)
}
