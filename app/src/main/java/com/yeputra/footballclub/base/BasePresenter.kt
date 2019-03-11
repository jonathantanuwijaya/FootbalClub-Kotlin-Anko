package com.yeputra.footballclub.base

import android.content.Context


/**
 * Created by yovi.putra
 *    on 10/Mar/2019 10:31
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
abstract class BasePresenter(private val activity: IBaseView): IBasePresenter {
    val contextView: Context by lazy { activity.getContextView() }
}