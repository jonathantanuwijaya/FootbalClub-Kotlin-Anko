package com.yeputra.footballclub.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yeputra.footballclub.detail.ui.UIDetailClub
import com.yeputra.footballclub.main.model.Club
import com.yeputra.footballclub.main.ui.UIListClub.Companion.intentData
import org.jetbrains.anko.setContentView

class DetailClubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val club: Club = intent.getParcelableExtra(intentData)

        UIDetailClub(club).setContentView(this)
    }
}
