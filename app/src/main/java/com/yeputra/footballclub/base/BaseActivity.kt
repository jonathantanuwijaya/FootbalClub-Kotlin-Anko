package com.yeputra.footballclub.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.yeputra.footballclub.utils.LoadingController


/**
 * Created by yovi.putra
 *    on 09/Mar/2019 10:56
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
abstract class BaseActivity<presenter: IBasePresenter>
    : AppCompatActivity(), IBaseView {

    private lateinit var loading: LoadingController

    protected lateinit var presenter: presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = initPresenter()

        loading = LoadingController(this)
    }

    abstract fun initPresenter(): presenter

    override fun getContextView(): Context = this

    override fun showProgressbar() = loading.showDialog()

    override fun hideProgressbar() = loading.hideDialog()

    override fun onPresenterSuccess(data: Any?) {
        Log.d(TAG, "onPresenterSuccess")
    }

    override fun onPresenterFailed(message: String?) {
        Log.d(TAG, "onPresenterFailed $message")
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    companion object {
        private val TAG: String? = BaseActivity::class.java.simpleName
    }
}