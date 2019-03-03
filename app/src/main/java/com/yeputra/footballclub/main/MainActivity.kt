package com.yeputra.footballclub.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yeputra.footballclub.main.data.DataManager
import com.yeputra.footballclub.main.ui.UIListClub
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UIListClub(
            DataManager(this).getClubs()
        ).setContentView(this)
    }
}
