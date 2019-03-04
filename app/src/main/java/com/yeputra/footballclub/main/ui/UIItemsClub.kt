package com.yeputra.footballclub.main.ui

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.yeputra.footballclub.utils.makeClickable
import org.jetbrains.anko.*


/**
 * Created by yovi.putra
 *    on 03/Mar/2019 17:28
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */

class UIItemsClub: AnkoComponent<ViewGroup>{

    companion object {
        const val img_logo_club = 1
        const val tv_name       = 2
    }

    @SuppressLint("ResourceType")
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
        linearLayout {
            orientation = LinearLayout.HORIZONTAL
            lparams(width = matchParent, height = wrapContent){
                padding = dip(16)
            }
            makeClickable()

            setupItemImage()

            setupItemName()
        }
    }

    private fun @AnkoViewDslMarker _LinearLayout.setupItemImage() {
        imageView {
            id = img_logo_club
        }.lparams(width = dip(42), height = dip(42)) {
            gravity = Gravity.CENTER_VERTICAL
        }
    }

    private fun @AnkoViewDslMarker _LinearLayout.setupItemName() {
        textView {
            id = tv_name
        }.lparams(width = matchParent) {
            gravity = Gravity.CENTER_VERTICAL
            margin = dip(8)
        }
    }

}