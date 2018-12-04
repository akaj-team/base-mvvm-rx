package com.android.asiantech.rx_mvvm_base.ui.user

import io.reactivex.subjects.BehaviorSubject

/**
 *
 * @author at-haingo
 */
interface UserVMContract {

    fun getAccessToken(): String

    fun loginStatus(): BehaviorSubject<Boolean>

    fun checkLogin()
}
