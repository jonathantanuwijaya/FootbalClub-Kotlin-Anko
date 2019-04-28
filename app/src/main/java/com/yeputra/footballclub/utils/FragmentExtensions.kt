package com.yeputra.footballclub.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

fun FragmentActivity.fragmentReplace(container: Int, fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(container, fragment, fragment::class.java.simpleName)
        .addToBackStack(null)
        .commit()
}

fun FragmentActivity.fragmentAdd(container: Int, fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .add(container, fragment, fragment::class.java.simpleName)
        .hide(fragment)
        .commit()
}

fun FragmentActivity.fragmentShow(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .show(fragment)
        .commit()
}

fun FragmentActivity.fragmentHide(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .hide(fragment)
        .commit()
}