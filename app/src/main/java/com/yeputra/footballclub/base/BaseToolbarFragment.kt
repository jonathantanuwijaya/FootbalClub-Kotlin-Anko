package com.yeputra.footballclub.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View


/**
 * Created by yovi.putra
 *    on 10/Mar/2019 11:23
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */

abstract class BaseToolbarFragment<presenter: IBasePresenter>
    : BaseFragment<presenter>(){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val baseActivity = (activity as AppCompatActivity)
        baseActivity.setSupportActionBar(setToolbar())
        baseActivity.supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    abstract fun setToolbar(): Toolbar?
}