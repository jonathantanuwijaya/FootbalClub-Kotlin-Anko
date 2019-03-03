package com.yeputra.footballclub.detail.ui

import android.view.Gravity
import android.view.View
import com.bumptech.glide.Glide
import com.yeputra.footballclub.detail.DetailClubActivity
import com.yeputra.footballclub.main.model.Club
import org.jetbrains.anko.*


/**
 * Created by yovi.putra
 *    on 03/Mar/2019 19:17
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
class UIDetailClub(val club: Club): AnkoComponent<DetailClubActivity> {
    override fun createView(ui: AnkoContext<DetailClubActivity>): View = with(ui){
        verticalLayout {
            lparams(width = matchParent)
            gravity = Gravity.CENTER_HORIZONTAL
            padding = dip(16)

            setupClubIcon()

            setupClubName()

            setupClubDescription()
        }
    }

    private fun @AnkoViewDslMarker _LinearLayout.setupClubIcon() {
        val imgIcon = imageView {

        }.lparams(width = dip(64), height = dip(64))
        Glide.with(context).load(club.icon).into(imgIcon)
    }

    private fun @AnkoViewDslMarker _LinearLayout.setupClubName() {
        textView {
            padding = dip(8)
            gravity = Gravity.CENTER_HORIZONTAL
            text = club.name
        }.lparams(width = matchParent)
    }

    private fun @AnkoViewDslMarker _LinearLayout.setupClubDescription() {
        textView {
            padding = dip(16)
            text = club.desc
        }.lparams(width = matchParent)
    }
}