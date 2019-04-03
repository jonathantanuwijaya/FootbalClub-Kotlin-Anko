package com.yeputra.footballclub.base

import android.content.Context


/**
 * Created by yovi.putra
 *    on 09/Mar/2019 10:55
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
interface IBaseView {

    fun getContextView(): Context

    fun showProgressbar()

    fun hideProgressbar()

    fun onPresenterSuccess(data: Any?)

    fun onPresenterFailed(message: String?)
}
