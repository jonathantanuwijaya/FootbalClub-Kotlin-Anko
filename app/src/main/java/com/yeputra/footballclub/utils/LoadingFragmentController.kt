package com.yeputra.footballclub.utils

import android.support.v4.app.DialogFragment


class LoadingFragmentController {

    companion object {
        private lateinit var dialog: DialogFragment
    }

    fun showDialog() {
        /*dialog = DialogFragment()
        dialog.isCancelable = false
        dialog.setStyle(R.style.ProgressBarStyle, DialogFragment.STYLE_NORMAL)
        dialog.showsDialog*/
    }

    fun hideDialog() {
        //dialog.dismiss()
    }
}