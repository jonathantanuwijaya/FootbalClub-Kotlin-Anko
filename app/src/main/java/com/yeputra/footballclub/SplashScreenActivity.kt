package com.yeputra.footballclub

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.yeputra.footballclub.base.BaseActivity
import com.yeputra.footballclub.presenter.LeaguePresenter
import com.yeputra.footballclub.ui.MainActivity

class SplashScreenActivity : BaseActivity<LeaguePresenter>() {
    override fun initPresenter(): LeaguePresenter = LeaguePresenter(this)
    private var delayHandler = Handler()
    private var runnable = Runnable {
        startActivity(
            Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        delayHandler.postDelayed(runnable, 3000)
    }

    override fun onDestroy() {
        delayHandler.removeCallbacks(runnable)
        super.onDestroy()
    }
}
