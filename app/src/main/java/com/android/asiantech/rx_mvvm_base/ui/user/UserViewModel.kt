package com.android.asiantech.rx_mvvm_base.ui.user

import com.android.asiantech.rx_mvvm_base.data.source.LocalRepository
import io.reactivex.subjects.BehaviorSubject

/**
 *
 * @author at-vinhhuynh
 */
class UserViewModel(private val localRepository: LocalRepository) : UserVMContract {

    private val loginStatus = BehaviorSubject.create<Boolean>()

    override fun checkLogin() {
        if (getAccessToken().isBlank()) {
            loginStatus.onNext(false)
        } else {
            loginStatus.onNext(true)
        }
    }

    override fun loginStatus() = loginStatus

    override fun getAccessToken() = localRepository.getApiToken()
}
