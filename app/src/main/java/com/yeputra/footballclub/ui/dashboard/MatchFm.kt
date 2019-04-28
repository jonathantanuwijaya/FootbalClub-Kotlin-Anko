package com.yeputra.footballclub.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.VPagerAdapter
import com.yeputra.footballclub.base.BaseFragment
import com.yeputra.footballclub.base.ITabView
import com.yeputra.footballclub.model.Pagers
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.ui.match.LastMatchFm
import com.yeputra.footballclub.ui.match.NextMatchFm
import kotlinx.android.synthetic.main.fragment_matchs.*

class MatchFm : BaseFragment<LeaguePresenter>() {
    override fun showProgressbar() {}

    override fun hideProgressbar() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? = inflater.inflate(R.layout.fragment_matchs,container,false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fragments = mutableListOf(
            Pagers(getContextView().getString(R.string.lbl_prev_match), LastMatchFm()),
            Pagers(getContextView().getString(R.string.lbl_next_match), NextMatchFm())
        )
        viewpager.adapter = VPagerAdapter(fragments, childFragmentManager)
        viewpager.overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun onResume() {
        super.onResume()

    }

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun onPrepareOptionsMenu(menu: Menu?) {
        menu?.findItem(R.id.menu_search)?.isVisible = true
        super.onPrepareOptionsMenu(menu)
        (activity as ITabView).getTabLayout()?.setupWithViewPager(viewpager)
        Log.d("A=","onPrepareOptionsMenu..")
    }
}
