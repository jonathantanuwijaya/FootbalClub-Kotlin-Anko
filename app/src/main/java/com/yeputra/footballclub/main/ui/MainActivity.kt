package com.yeputra.footballclub.main.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yeputra.footballclub.R
import com.yeputra.footballclub.main.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager.adapter = ViewPagerAdapter(supportFragmentManager)
    }
}
