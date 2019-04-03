package com.yeputra.footballclub.base

import android.support.v7.widget.Toolbar

abstract class BaseToolbarActivity<presenter: IBasePresenter>
    : BaseActivity<presenter>() {

    override fun onStart() {
        super.onStart()
        setSupportActionBar(setToolbar())
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(setButtonBack())
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if(setButtonBack()){
            setToolbar()?.setNavigationOnClickListener {
                finish()
            }
        }
    }

    protected fun setToolbarTitle(toolbarTitle: String) {
        supportActionBar?.title = toolbarTitle
    }

    protected fun setToolbarTitle(toolbarTitle: Int) {
        supportActionBar?.setTitle(toolbarTitle)
    }

    abstract fun setButtonBack(): Boolean

    abstract fun setToolbar(): Toolbar?
}