package com.yeputra.footballclub.base

import android.graphics.drawable.Drawable
import android.support.v7.widget.Toolbar

abstract class BaseToolbarActivity<presenter: IBasePresenter>
    : BaseActivity<presenter>(), IToolbar {

    override fun onStart() {
        super.onStart()
        setSupportActionBar(setToolbar())
        supportActionBar?.setDisplayShowTitleEnabled(setToolbarTitle())
        supportActionBar?.setDisplayHomeAsUpEnabled(setButtonBack())
        supportActionBar?.setDisplayShowHomeEnabled(setToolbarIcon())

        if(setButtonBack()){
            setToolbar()?.setNavigationOnClickListener {
                finish()
            }
        }
    }

    protected fun setToolbarTitle(toolbarTitle: String?) {
        supportActionBar?.title = toolbarTitle
    }

    protected fun setToolbarTitle(toolbarTitle: Int) {
        supportActionBar?.setTitle(toolbarTitle)
    }

    protected fun setToolbarSubTitle(toolbarTitle: String?) {
        supportActionBar?.subtitle = toolbarTitle
    }

    protected fun setToolbarSubTitle(toolbarTitle: Int) {
        supportActionBar?.setSubtitle(toolbarTitle)
    }

    protected fun setToolbarIcon(image: Int) {
        supportActionBar?.setIcon(image)
    }

    protected fun setToolbarIcon(image: Drawable?) {
        supportActionBar?.setIcon(image)
    }

    override fun setButtonBack(): Boolean = false

    override fun setToolbarTitle(): Boolean = false

    override fun setToolbarSubTitle(): Boolean = false

    override fun setToolbarIcon(): Boolean = false

    abstract fun setToolbar(): Toolbar?


}