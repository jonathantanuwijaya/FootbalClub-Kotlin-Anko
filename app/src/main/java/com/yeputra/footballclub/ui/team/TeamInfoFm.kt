package com.yeputra.footballclub.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.base.BaseFragment
import com.yeputra.footballclub.model.Team
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.utils.INTENT_DATA
import kotlinx.android.synthetic.main.fragment_teaminfo.*

class TeamInfoFm : BaseFragment<LeaguePresenter>() {
    private lateinit var team: Team

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? = inflater.inflate(R.layout.fragment_teaminfo,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initViewConfigure()
    }

    private fun initData() {
        arguments?.getParcelable<Team>(INTENT_DATA)?.let {
            team = it
        }
    }

    private fun initViewConfigure(){
        tv_desc.text = team.description
        tv_stadium.text = team.stadium
    }

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun showProgressbar() {}

    override fun hideProgressbar() {}

}
