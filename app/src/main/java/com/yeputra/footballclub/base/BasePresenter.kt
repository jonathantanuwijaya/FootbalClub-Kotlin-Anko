package com.yeputra.footballclub.base

import android.content.Context
import com.yeputra.footballclub.R
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer


/**
 * Created by yovi.putra
 *    on 10/Mar/2019 10:31
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
abstract class BasePresenter(private val activity: IBaseView): IBasePresenter {
    protected val contextView: Context by lazy { activity.getContextView() }

    protected var subscriber: Disposable? = null

    override fun onDestroyPresenter() {
        subscriber?.dispose()
    }

    fun onSuccess():Consumer<Any> =
        Consumer{
            activity.onPresenterSuccess(it)
            activity.hideProgressbar()
        }

    fun onFailed():Consumer<Any> =
        Consumer{
            activity.hideProgressbar()
            activity.onPresenterFailed(activity.getContextView().getString(R.string.no_internet_connection))
        }
}