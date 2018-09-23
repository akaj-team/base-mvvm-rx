package com.android.asiantech.rx_mvvm_base.ui.user.login

import com.android.asiantech.rx_mvvm_base.data.source.Repository
import io.reactivex.subjects.BehaviorSubject

/**
 *
 * @author at-vinhhuynh
 */
class LoginViewModel(private val repository: Repository) : LoginVMContract {

    override fun progressDialogStatus(): BehaviorSubject<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validateLoginInformation(): BehaviorSubject<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun login() = repository.login()

}
