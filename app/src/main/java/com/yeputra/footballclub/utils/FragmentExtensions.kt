package com.yeputra.footballclub.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

fun FragmentActivity.changeTo(container: Int, fragment: Fragment) {

    supportFragmentManager
        .beginTransaction()
        .replace(container, fragment, fragment::class.java.simpleName)
        .commit()
}