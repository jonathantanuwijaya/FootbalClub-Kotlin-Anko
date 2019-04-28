package com.yeputra.footballclub.base

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar


/**
 * Created by yovi.putra
 *    on 10/Mar/2019 11:23
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */

abstract class BaseToolbarFragment<presenter: IBasePresenter>
    : BaseFragment<presenter>(){

    override fun onStart() {
        super.onStart()
        configToolbar()
    }

    private fun configToolbar() {
        setHasOptionsMenu(true)
        val baseActivity = (activity as AppCompatActivity)
        baseActivity.setSupportActionBar(setToolbar())
        baseActivity.supportActionBar?.setDisplayShowTitleEnabled(false)
        baseActivity.supportActionBar?.setDisplayHomeAsUpEnabled(setButtonBack())

        if(setButtonBack()){
            setToolbar()?.setNavigationOnClickListener {
                activity?.finish()
            }
        }
    }

    abstract fun setToolbar(): Toolbar?

    abstract fun setButtonBack(): Boolean
}