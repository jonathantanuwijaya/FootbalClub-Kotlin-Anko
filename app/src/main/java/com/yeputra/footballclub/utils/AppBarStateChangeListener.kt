package com.yeputra.footballclub.utils

import android.support.design.widget.AppBarLayout

class AppBarStateChangeListener(
    private val listener: (state: State) -> Unit
) : AppBarLayout.OnOffsetChangedListener {

    private var mCurrentState = State.SWIPING
    private var latestPos = 0
    // State
    enum class State {
        EXPANDED,
        COLLAPSED,
        SWIPING
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        val pos = Math.abs(i)

        when {
            i == 0 -> {
                updateState(State.EXPANDED)
            }
            pos == appBarLayout.totalScrollRange -> {
                updateState(State.COLLAPSED)
            }
            else -> {
                mCurrentState = State.EXPANDED
                updateState(State.SWIPING)
            }
        }
        latestPos = pos
    }

    fun updateState(newState: State) {
        if (mCurrentState != newState) {
            listener(newState)
        }
        mCurrentState = newState
    }
}