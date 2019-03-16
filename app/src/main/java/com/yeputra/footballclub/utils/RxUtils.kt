package com.yeputra.footballclub.utils

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by yovi.putra
 *    on 16/Mar/2019 13:40
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
object RxUtils {
    fun <T> applyObservableAsync(): ObservableTransformer<T, T> {
        return object : ObservableTransformer<T, T> {
            override fun apply(observable: Observable<T>): ObservableSource<T> {
                return observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}