package com.yeputra.footballclub.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @SuppressLint("SimpleDateFormat")
    fun format(date: Date, format: String): String =
        SimpleDateFormat(format).format(date)

    @SuppressLint("SimpleDateFormat")
    fun parser(date: String?, format: String): Date =
        SimpleDateFormat(format).parse(date)
}