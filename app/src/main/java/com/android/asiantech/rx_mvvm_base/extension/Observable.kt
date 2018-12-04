package com.android.asiantech.rx_mvvm_base.extension

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Use this extension for Observable
 * @author at-haingo
 */
internal fun <T> Observable<T>.observeOnUiThread(): Observable<T>
        = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

internal fun <T> Single<T>.observeOnUiThread(): Single<T>
        = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

