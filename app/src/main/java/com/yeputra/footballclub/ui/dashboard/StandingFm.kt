package com.yeputra.footballclub.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.MatchAdapter
import com.yeputra.footballclub.base.BaseFragment
import com.yeputra.footballclub.model.EventsResponse
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.ui.details.DetailMatchActivity
import com.yeputra.footballclub.utils.INTENT_DATA
import com.yeputra.footballclub.utils.league
import kotlinx.android.synthetic.main.fragment_standing.*

class StandingFm : BaseFragment<LeaguePresenter>() {
    private lateinit var matchAdapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? = inflater.inflate(R.layout.fragment_standing,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initViewConfigure()
    }

    private fun initData() {
        matchAdapter = MatchAdapter(mutableListOf()){
            val intent = Intent(context, DetailMatchActivity::class.java)
            intent.putExtra(INTENT_DATA, it.idEvent)
            context?.startActivity(intent)
        }
        presenter.getLastMatch(league)
    }

    private fun initViewConfigure(){
        rv_match.layoutManager = LinearLayoutManager(context)
        rv_match.adapter = matchAdapter

        swipe_container.setColorSchemeColors(
            ContextCompat.getColor(getContextView(),R.color.colorPrimary),
            ContextCompat.getColor(getContextView(),R.color.colorPrimaryDark),
            ContextCompat.getColor(getContextView(),R.color.colorTextViewSecondary),
            ContextCompat.getColor(getContextView(),R.color.devider)
        )

        swipe_container.setOnRefreshListener {
            presenter.getLastMatch(league)
        }
    }

    override fun onPresenterSuccess(data: Any?) {
        super.onPresenterSuccess(data)

        when(data){
            is EventsResponse ->
                data.events?.let { matchAdapter.replaceItem(it) }
        }
    }

    override fun showProgressbar() {
        swipe_container?.isRefreshing = true
    }

    override fun hideProgressbar() {
        swipe_container?.isRefreshing = false
    }

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)
}
