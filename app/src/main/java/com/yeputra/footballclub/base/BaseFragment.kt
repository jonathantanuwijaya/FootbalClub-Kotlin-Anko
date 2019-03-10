package com.yeputra.footballclub.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast


/**
 * Created by yovi.putra
 *    on 10/Mar/2019 11:23
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */

abstract class BaseFragment<presenter: IBasePresenter>
    : Fragment(), IBaseView{

    protected lateinit var activity: IBaseView
    protected lateinit var presenter: presenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context?.let { it ->
            activity = it as IBaseView
        }?: run{
            activity = super.getContext() as IBaseView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = initPresenter()
    }

    abstract fun initPresenter(): presenter

    override fun showProgressbar() = activity.showProgressbar()

    override fun hideProgressbar() = activity.hideProgressbar()

    override fun onPresenterSuccess(data: Any?) {}

    override fun onPresenterFailed(message: String?) {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }
}