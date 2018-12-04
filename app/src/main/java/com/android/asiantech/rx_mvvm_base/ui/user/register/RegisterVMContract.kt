package com.android.asiantech.rx_mvvm_base.ui.user.register

import com.android.asiantech.rx_mvvm_base.data.source.remote.response.SignUpResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

/**
 *
 * @author at-haingo
 */
interface RegisterVMContract {

    /**
     * Register new account
     * @param email is email used register
     * @param password is password used register
     * @return signUp status
     */
    fun register(email: String, password: String, avatar: String): Single<SignUpResponse>

    /**
     * @return information validate status
     */
    fun infoValidateStatus(): BehaviorSubject<Boolean>

    /**
     * Validate register information
     * @param email is email used register new account
     * @param password is password used register new account
     * @param confirmPassword is confirm password used register new account
     */
    fun validateRegisterInformation(email: String, password: String, confirmPassword: String)

    /**
     * @return progress dialog status
     */
    fun progressDialogStatus(): BehaviorSubject<Boolean>
}
