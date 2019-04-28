package com.yeputra.footballclub.ui.dashboard

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yeputra.footballclub.R
import com.yeputra.footballclub.base.BaseFragment
import com.yeputra.footballclub.model.League
import com.yeputra.footballclub.model.LeaguesResponse
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.utils.DateUtils
import com.yeputra.footballclub.utils.league
import com.yeputra.footballclub.utils.toast
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFm : BaseFragment<LeaguePresenter>() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initViewConfigure()
    }

    private fun initData() {
        presenter.getLeague(league.idLeague.toString())
    }

    private fun initViewConfigure(){
        swiperefresh.setColorSchemeColors(
            ContextCompat.getColor(getContextView(),R.color.colorPrimary),
            ContextCompat.getColor(getContextView(),R.color.colorPrimaryDark),
            ContextCompat.getColor(getContextView(),R.color.colorAccent),
            ContextCompat.getColor(getContextView(),R.color.devider)
        )

        swiperefresh.setOnRefreshListener {
            presenter.getStanding(league.idLeague.toString())
        }
    }

    override fun onPresenterSuccess(data: Any?) {
        super.onPresenterSuccess(data)
        when(data){
            is LeaguesResponse ->{
                try{
                    league = data.leagues?.get(0) as League

                    Glide.with(this)
                        .load(league.logo)
                        .apply(RequestOptions().placeholder(R.drawable.logo))
                        .into(img_logo)

                    tv_name.text = league.name
                    tv_website.text = league.website

                    tv_country.text = league.country
                    tv_formed_year.text = league.formedYear
                    tv_date_first_event.text = DateUtils.parser(league.dateFirstEvent, "yyyy-MM-dd", "dd-MMM-yyyy")
                    tv_desc.text = league.description
                }catch (e: Exception){
                    context?.toast("Data tidak tersedia")
                }
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        menu?.findItem(R.id.menu_search)?.isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    override fun showProgressbar() {
        swiperefresh?.isRefreshing = true
    }

    override fun hideProgressbar() {
        swiperefresh?.isRefreshing = false
    }

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)
}
