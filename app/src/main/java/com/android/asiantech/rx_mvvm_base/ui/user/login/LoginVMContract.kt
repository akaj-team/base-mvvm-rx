package com.android.asiantech.rx_mvvm_base.ui.user.login

import com.android.asiantech.rx_mvvm_base.data.source.remote.response.LoginResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

/**
 *
 * @author at-vinhhuynh
 */
interface LoginVMContract {

    fun login(email: String, password: String): Single<LoginResponse>

    fun infoValidateStatus(): BehaviorSubject<Boolean>

    fun progressDialogStatus(): BehaviorSubject<Boolean>

    fun validateLoginInformation(email: String, password: String)

    fun saveApiToken(token: String)
}
