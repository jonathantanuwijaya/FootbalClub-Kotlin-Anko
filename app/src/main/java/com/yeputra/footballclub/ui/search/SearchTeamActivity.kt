package com.yeputra.footballclub.ui.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.jakewharton.rxbinding.widget.RxTextView
import com.yeputra.footballclub.R
import com.yeputra.footballclub.adapter.MatchAdapter
import com.yeputra.footballclub.base.BaseToolbarActivity
import com.yeputra.footballclub.model.SearchMatchResponse
import com.yeputra.footballclub.presenter.LeaguePresenter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.app_bar_search.*
import java.util.concurrent.TimeUnit


class SearchTeamActivity : BaseToolbarActivity<LeaguePresenter>() {
    private lateinit var matchAdapter: MatchAdapter

    override fun setButtonBack(): Boolean = true

    override fun setToolbar(): Toolbar? = toolbar

    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        matchAdapter = MatchAdapter(mutableListOf()){

        }

        rv_match.layoutManager = LinearLayoutManager(this)
        rv_match.adapter = matchAdapter

        RxTextView.textChangeEvents(et_finder)
            .debounce(500, TimeUnit.MILLISECONDS)
            .filter { !it.text().toString().isEmpty()}
            .subscribe {
                presenter.searchEvent(it.text().toString())
            }
    }

    override fun onPresenterSuccess(data: Any?) {
        super.onPresenterSuccess(data)
        when(data){
            is SearchMatchResponse -> {
                data.event?.let { matchAdapter.replaceItem(it) }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return true
    }
}
