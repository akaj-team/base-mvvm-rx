package com.android.asiantech.rx_mvvm_base.ui.user.login

import com.android.asiantech.rx_mvvm_base.data.source.remote.response.LoginResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

/**
 *
 * @author at-haingo
 */
interface LoginVMContract {

    /**
     * @param email is user email
     * @param password is user password
     * @return loginResponse contain access token
     */
    fun login(email: String, password: String): Single<LoginResponse>

    /**
     * @return information validate status
     */
    fun infoValidateStatus(): BehaviorSubject<Boolean>

    /**
     * @return progress dialog status
     */
    fun progressDialogStatus(): BehaviorSubject<Boolean>

    /**
     * Validate user account
     * @param email is user email
     * @param password is user password
     */
    fun validateLoginInformation(email: String, password: String)

    /**
     * Save token to SharePreference
     */
    fun saveApiToken(token: String)
}
