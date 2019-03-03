package com.yeputra.footballclub.ui

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yeputra.footballclub.activity.MainActivity
import com.yeputra.footballclub.adapter.ListClubAdapter
import com.yeputra.footballclub.model.Club
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView


/**
 * Created by yovi.putra
 *    on 03/Mar/2019 17:03
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */

class UIListClub(var clubs: MutableList<Club>): AnkoComponent<MainActivity>, AnkoLogger {
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui){
        debug("Create UI list club")

        verticalLayout {
            lparams(width = matchParent, height = matchParent){}

            recyclerView {
                lparams(width = matchParent, height = matchParent)
                layoutManager = LinearLayoutManager(context)
                adapter = ListClubAdapter(clubs){
                    toast("Hello ${it.name}")
                    startActivity<SecondActivity>("name" to "dicoding")
                }
            }
        }
    }
}