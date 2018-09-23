package com.android.asiantech.rx_mvvm_base.ui.user.register

import com.android.asiantech.rx_mvvm_base.data.source.Repository
import io.reactivex.subjects.BehaviorSubject

/**
 *
 * @author at-vinhhuynh
 */
class RegisterViewModel(private val repository: Repository) : RegisterVMContract {

    override fun progressDialogStatus(): BehaviorSubject<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun register(email: String, password: String) = repository.register(email, password)

    override fun validateRegisterInformation(): BehaviorSubject<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
