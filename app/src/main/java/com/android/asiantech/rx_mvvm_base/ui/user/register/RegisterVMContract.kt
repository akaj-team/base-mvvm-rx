package com.android.asiantech.rx_mvvm_base.ui.user.register

import io.reactivex.subjects.BehaviorSubject

/**
 *
 * @author at-vinhhuynh
 */
interface RegisterVMContract {

    fun register(email: String, password: String)

    fun validateRegisterInformation(): BehaviorSubject<Boolean>

    fun progressDialogStatus(): BehaviorSubject<Boolean>
}
