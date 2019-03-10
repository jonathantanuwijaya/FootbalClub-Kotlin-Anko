package com.yeputra.footballclub.base


/**
 * Created by yovi.putra
 *    on 09/Mar/2019 10:55
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
interface IBaseView {
    fun showProgressbar()

    fun hideProgressbar()

    fun onPresenterSuccess(data: Any?)

    fun onPresenterFailed(message: String?)
}