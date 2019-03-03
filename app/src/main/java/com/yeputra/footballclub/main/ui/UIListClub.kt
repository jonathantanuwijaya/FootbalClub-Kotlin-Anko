package com.yeputra.footballclub.main.ui

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yeputra.footballclub.detail.DetailClubActivity
import com.yeputra.footballclub.main.MainActivity
import com.yeputra.footballclub.main.adapter.ListClubAdapter
import com.yeputra.footballclub.main.model.Club
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView


/**
 * Created by yovi.putra
 *    on 03/Mar/2019 17:03
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */

class UIListClub(var clubs: MutableList<Club>): AnkoComponent<MainActivity>, AnkoLogger {
    companion object {
        val intentData = "intentData"
    }

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui){
        debug("Create UI list club")

        verticalLayout {
            lparams(width = matchParent, height = matchParent){}

            recyclerView {
                lparams(width = matchParent, height = matchParent)
                layoutManager = LinearLayoutManager(context)
                adapter = ListClubAdapter(clubs){
                    startActivity<DetailClubActivity>(intentData to it)
                }
            }
        }
    }
}