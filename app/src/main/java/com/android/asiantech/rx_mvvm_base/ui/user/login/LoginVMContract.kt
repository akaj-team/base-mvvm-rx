package com.android.asiantech.rx_mvvm_base.ui.user.login

import io.reactivex.subjects.BehaviorSubject

/**
 *
 * @author at-vinhhuynh
 */
interface LoginVMContract {

    fun login()

    fun validateLoginInformation(): BehaviorSubject<Boolean>

    fun progressDialogStatus(): BehaviorSubject<Boolean>
}
