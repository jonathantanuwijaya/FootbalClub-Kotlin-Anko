package com.yeputra.footballclub

import android.content.Intent
import android.os.Bundle
import com.yeputra.footballclub.base.BaseActivity
import com.yeputra.footballclub.model.LeaguesResponse
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.ui.LeagueActivity
import com.yeputra.footballclub.utils.INTENT_DATA

class SplashScreenActivity : BaseActivity<LeaguePresenter>() {
    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        presenter.getLeagues()
    }

    override fun onPresenterSuccess(data: Any?) {
        super.onPresenterSuccess(data)
        when(data){
            is LeaguesResponse -> {
                startActivity(
                    Intent(this, LeagueActivity::class.java)
                        .putExtra(INTENT_DATA, data))
                finish()
            }
        }
    }

    override fun showProgressbar() {}

    override fun hideProgressbar() {}
}
