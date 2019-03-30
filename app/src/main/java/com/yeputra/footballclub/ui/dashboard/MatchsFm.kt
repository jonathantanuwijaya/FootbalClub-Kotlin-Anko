package com.yeputra.footballclub.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.VPMatchAdapter
import com.yeputra.footballclub.base.BaseFragment
import com.yeputra.footballclub.presenter.LeaguePresenter
import kotlinx.android.synthetic.main.fragment_matchs.*

class MatchsFm : BaseFragment<LeaguePresenter>() {
    override fun showProgressbar() {}

    override fun hideProgressbar() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? = inflater.inflate(R.layout.fragment_matchs,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initViewConfigure()
    }

    private fun initData() {

    }

    private fun initViewConfigure(){
        viewpager.adapter = activity?.supportFragmentManager?.let { VPMatchAdapter(it) }
        //tab.setupWithViewPager(viewpager)
        viewpager.overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)
}
