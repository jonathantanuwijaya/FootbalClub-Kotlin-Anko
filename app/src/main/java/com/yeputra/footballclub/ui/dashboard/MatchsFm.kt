package com.yeputra.footballclub.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.*
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.VPMatchAdapter
import com.yeputra.footballclub.base.BaseToolbarFragment
import com.yeputra.footballclub.model.Pagers
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.ui.match.LastMatchFm
import com.yeputra.footballclub.ui.match.NextMatchFm
import com.yeputra.footballclub.ui.search.SearchMatchActivity
import kotlinx.android.synthetic.main.app_bar_tab.*
import kotlinx.android.synthetic.main.fragment_matchs.*

class MatchsFm : BaseToolbarFragment<LeaguePresenter>() {
    override fun setToolbar(): Toolbar? = toolbar

    override fun showProgressbar() {}

    override fun hideProgressbar() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? = inflater.inflate(R.layout.fragment_matchs,container,false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar_title.text = context?.getString(R.string.botnav_match)
        val fragments = mutableListOf(
            Pagers(getContextView().getString(R.string.lbl_prev_match), LastMatchFm()),
            Pagers(getContextView().getString(R.string.lbl_next_match), NextMatchFm())
        )
        viewpager.adapter = fragmentManager?.let { VPMatchAdapter(fragments, it) }
        tab.setupWithViewPager(viewpager)
        viewpager.overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_search ->
                startActivity(Intent(context, SearchMatchActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
