package com.yeputra.footballclub.ui

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*


/**
 * Created by yovi.putra
 *    on 03/Mar/2019 17:28
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */

class UIItemsClub: AnkoComponent<ViewGroup>{

    companion object Id{
        val img_logo_club = 1
        val tv_name       = 2
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
        linearLayout() {
            orientation = LinearLayout.HORIZONTAL
            lparams(width = matchParent, height = wrapContent){
                padding = dip(16)
            }

            imageView {
                id = img_logo_club
            }.lparams(width = dip(42), height = dip(42)){
                gravity = Gravity.CENTER_VERTICAL
            }

            textView{
                id = tv_name
            }.lparams(width = matchParent){
                gravity = Gravity.CENTER_VERTICAL
                margin = dip(8)
            }
        }
    }
}