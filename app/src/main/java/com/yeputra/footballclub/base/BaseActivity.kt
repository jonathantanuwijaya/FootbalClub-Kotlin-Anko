package com.yeputra.footballclub.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.yeputra.footballclub.utils.toast


/**
 * Created by yovi.putra
 *    on 09/Mar/2019 10:56
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
abstract class BaseActivity<presenter: IBasePresenter>
    : AppCompatActivity(), IBaseView {

    protected lateinit var presenter: presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = initPresenter()
    }

    override fun onDestroy() {
        presenter.onDestroyPresenter()
        super.onDestroy()
    }

    abstract fun initPresenter(): presenter

    override fun getContextView(): Context = this

    override fun showProgressbar() {}

    override fun hideProgressbar() {}

    override fun onPresenterSuccess(data: Any?) {
        Log.d(TAG, "onPresenterSuccess")
    }

    override fun onPresenterFailed(message: String?) {
        Log.d(TAG, "onPresenterFailed $message")
        toast(message?: "")
    }

    companion object {
        private val TAG: String? = BaseActivity::class.java.simpleName
    }
}