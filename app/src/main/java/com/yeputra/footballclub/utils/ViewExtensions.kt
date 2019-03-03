package com.yeputra.footballclub

import android.util.TypedValue
import android.view.View
import org.jetbrains.anko.backgroundResource


/**
 * Created by yovi.putra
 *    on 03/Mar/2019 20:18
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */

fun View.makeClickable() {
    val outValue = TypedValue()
    context.theme.resolveAttribute(
        android.R.attr.selectableItemBackground,
        outValue,
        true)
    backgroundResource = outValue.resourceId
}