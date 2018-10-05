package com.android.asiantech.rx_mvvm_base.ui.user.register

import com.android.asiantech.rx_mvvm_base.data.source.remote.response.SignUpResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

/**
 *
 * @author at-vinhhuynh
 */
interface RegisterVMContract {

    fun register(email: String, password: String, avatar: String): Single<SignUpResponse>

    fun infoValidateStatus(): BehaviorSubject<Boolean>

    fun validateRegisterInformation(email: String, password: String, confirmPassword: String)

    fun progressDialogStatus(): BehaviorSubject<Boolean>
}
