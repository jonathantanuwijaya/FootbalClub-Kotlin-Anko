package com.yeputra.footballclub.utils

import android.util.TypedValue
import android.view.View


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
    this.setBackgroundResource(outValue.resourceId)
}