package com.yeputra.footballclub.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.Toast


/**
 * Created by yovi.putra
 *    on 10/Mar/2019 11:23
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */

abstract class BaseFragment<presenter: IBasePresenter>
    : Fragment(), IBaseView{

    private lateinit var activity: IBaseView
    protected lateinit var presenter: presenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context?.let {
            Log.d(TAG, "Get context from fragment attach")
            activity = it as IBaseView
        }?: run{
            Log.d(TAG, "Get context from super")
            activity = this
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = initPresenter()
    }

    abstract fun initPresenter(): presenter

    override fun getContextView(): Context = context as Context

    override fun onPresenterSuccess(data: Any?) {
        Log.d(TAG, "onPresenterSuccess")
    }

    override fun onPresenterFailed(message: String?) {
        Log.d(TAG, "onPresenterFailed")
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        presenter.onDestroyPresenter()
        super.onDestroyView()
    }

    companion object {
        private const val TAG: String = "BaseFragment"
    }
}