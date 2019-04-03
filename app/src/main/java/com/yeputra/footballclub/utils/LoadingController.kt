package com.yeputra.footballclub.utils

import android.app.Activity
import android.app.Dialog
import android.view.Window
import com.yeputra.footballclub.R


class LoadingController(private val activity: Activity) {

    private var dialog: Dialog? = null

    fun showDialog() {
        dialog = Dialog(activity)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.loading)
        dialog?.show()
    }

    fun hideDialog() {
        dialog?.dismiss()
    }
}