package com.android.asiantech.rx_mvvm_base.ui.user.login

import com.android.asiantech.rx_mvvm_base.data.source.LocalRepository
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.ui.user.register.RegisterViewModel
import io.reactivex.subjects.BehaviorSubject
import java.util.regex.Pattern

/**
 *
 * @author at-vinhhuynh
 */
class LoginViewModel(private val repository: Repository, private val localRepository: LocalRepository) : LoginVMContract {

    private var emailPattern = Pattern.compile(RegisterViewModel.EMAIL_VALIDATE_EXPRESSION, Pattern.CASE_INSENSITIVE)
    private val processDialogStatus = BehaviorSubject.create<Boolean>()
    private val validateRegisterInformationStatus = BehaviorSubject.create<Boolean>()

    override fun progressDialogStatus() = processDialogStatus

    override fun infoValidateStatus() = validateRegisterInformationStatus

    override fun login(email: String, password: String) = repository.login(email, password)
            .doOnSubscribe {
                processDialogStatus.onNext(true)
            }.doFinally {
                processDialogStatus.onNext(false)
            }

    override fun validateLoginInformation(email: String, password: String) {
        if (email.isEmpty()
                || password.length < RegisterViewModel.MIN_LENGTH_PASSWORD
                || !emailPattern.matcher(email).matches()) {
            validateRegisterInformationStatus.onNext(false)
        } else {
            validateRegisterInformationStatus.onNext(true)
        }
    }

    override fun saveApiToken(token: String) = localRepository.saveApiToken(token)
}
