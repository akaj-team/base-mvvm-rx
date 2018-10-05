package com.android.asiantech.rx_mvvm_base.ui.user.register

import com.android.asiantech.rx_mvvm_base.data.source.Repository
import io.reactivex.subjects.BehaviorSubject
import java.util.regex.Pattern

/**
 *
 * @author at-vinhhuynh
 */
class RegisterViewModel(private val repository: Repository) : RegisterVMContract {

    companion object {
        internal const val MIN_LENGTH_PASSWORD = 8
        internal const val EMAIL_VALIDATE_EXPRESSION = "^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\$"
    }

    private var emailPattern = Pattern.compile(EMAIL_VALIDATE_EXPRESSION, Pattern.CASE_INSENSITIVE)

    private val processDialogStatus = BehaviorSubject.create<Boolean>()
    private val validateRegisterInformationStatus = BehaviorSubject.create<Boolean>()

    override fun progressDialogStatus() = processDialogStatus

    override fun infoValidateStatus() = validateRegisterInformationStatus

    override fun register(email: String, password: String, avatar: String) =
            repository.register(email, password, avatar)
                    .doOnSubscribe {
                        processDialogStatus.onNext(true)
                    }.doFinally {
                        processDialogStatus.onNext(false)
                    }

    override fun validateRegisterInformation(email: String, password: String, confirmPassword: String) {
        if (email.isEmpty()
                || password.length < MIN_LENGTH_PASSWORD
                || confirmPassword.length < MIN_LENGTH_PASSWORD
                || password != confirmPassword
                || !emailPattern.matcher(email).matches()) {
            validateRegisterInformationStatus.onNext(false)
        } else {
            validateRegisterInformationStatus.onNext(true)
        }
    }
}
