package com.yeputra.footballclub.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.FavoriteAdapter
import com.yeputra.footballclub.base.BaseFragment
import com.yeputra.footballclub.presenter.MatchFavoritePresenter
import com.yeputra.footballclub.ui.details.DetailMatchActivity
import com.yeputra.footballclub.utils.INTENT_DATA
import kotlinx.android.synthetic.main.app_bar_tab.*
import kotlinx.android.synthetic.main.list_match.*

class FavoriteFm : BaseFragment<MatchFavoritePresenter>() {

    private lateinit var matchAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? = inflater.inflate(R.layout.list_match,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initViewConfigure()
    }

    private fun initData() {
        matchAdapter = FavoriteAdapter(mutableListOf()){
            val intent = Intent(context, DetailMatchActivity::class.java)
            intent.putExtra(INTENT_DATA, it.eventId)
            context?.startActivity(intent)
        }

        getRepository()
    }

    override fun onResume() {
        super.onResume()
        getRepository()
    }

    private fun getRepository(){
        presenter.findAll {
            matchAdapter.replaceItem(it)
        }
    }

    private fun initViewConfigure() {
        toolbar_title.text = context?.getString(R.string.botnav_favorite)
        rv_match.layoutManager = LinearLayoutManager(context)
        rv_match.adapter = matchAdapter

        swipe_container.setColorSchemeColors(
            ContextCompat.getColor(getContextView(),R.color.colorPrimary),
            ContextCompat.getColor(getContextView(),R.color.colorPrimaryDark),
            ContextCompat.getColor(getContextView(),R.color.colorTextViewSecondary),
            ContextCompat.getColor(getContextView(),R.color.devider)
        )

        swipe_container.setOnRefreshListener {
            getRepository()
        }
    }

    override fun showProgressbar() {
        swipe_container.isRefreshing = true
    }

    override fun hideProgressbar() {
        swipe_container.isRefreshing = false
    }

    override fun initPresenter(): MatchFavoritePresenter = MatchFavoritePresenter(this)
}
