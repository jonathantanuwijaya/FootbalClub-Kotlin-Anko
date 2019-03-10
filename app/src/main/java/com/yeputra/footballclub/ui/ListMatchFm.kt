package com.yeputra.footballclub.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.ListClubAdapter
import com.yeputra.footballclub.base.BaseFragment
import com.yeputra.footballclub.presenter.LeaguePresenter
import kotlinx.android.synthetic.main.list_match.*

class ListMatchFm : BaseFragment<LeaguePresenter>() {

    private lateinit var matchAdapter: ListClubAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? = inflater.inflate(R.layout.list_match,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        matchAdapter = ListClubAdapter(mutableListOf()){}
        rv_match.layoutManager = LinearLayoutManager(context)
        rv_match.adapter = matchAdapter

        showProgressbar()
    }

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(activity)
}
