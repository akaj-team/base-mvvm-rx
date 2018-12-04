package com.android.asiantech.rx_mvvm_base.ui.user.register

import android.app.ProgressDialog
import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiException
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.SignUpResponse
import com.android.asiantech.rx_mvvm_base.extension.observeOnUiThread
import com.android.asiantech.rx_mvvm_base.extension.popFragment
import com.android.asiantech.rx_mvvm_base.extension.showAlert
import com.android.asiantech.rx_mvvm_base.ui.base.BaseFragment
import com.android.asiantech.rx_mvvm_base.ui.user.UserActivity
import kotlinx.android.synthetic.main.fragment_register.*

/**
 *
 * @author at-haingo
 */
class RegisterFragment : BaseFragment() {

    private lateinit var viewModel: RegisterVMContract
    private lateinit var progressDialog: ProgressDialog

    companion object {
        internal fun newInstance() = RegisterFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = RegisterViewModel(Repository())
        initView()
        initListener()
    }

    override fun onBindViewModel() {
        addDisposables(
                viewModel.progressDialogStatus()
                        .observeOnUiThread()
                        .subscribe(this::handleProgressDialogStatus),
                viewModel.infoValidateStatus()
                        .observeOnUiThread()
                        .subscribe(this::handleValidateRegisterInformation)
        )
    }

    private fun initView() {
        progressDialog = ProgressDialog(context)
        progressDialog.setCancelable(false)

        tvBackToLogin.paintFlags = tvBackToLogin.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    private fun initListener() {
        tvBackToLogin.setOnClickListener {
            (activity as? UserActivity)?.popFragment()
        }

        btnRegister.setOnClickListener {
            viewModel.register(edtEmail.text.toString(), edtPassword.text.toString(), "")
                    .observeOnUiThread()
                    .subscribe(this::handleRegisterSuccess,
                            this::handleRegisterError)
        }

        edtEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.validateRegisterInformation(edtEmail.text.toString(),
                        edtPassword.text.toString(),
                        edtConfirmPassword.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        edtPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.validateRegisterInformation(edtEmail.text.toString(),
                        edtPassword.text.toString(),
                        edtConfirmPassword.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        edtConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.validateRegisterInformation(edtEmail.text.toString(),
                        edtPassword.text.toString(),
                        edtConfirmPassword.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }

    private fun handleRegisterSuccess(registerResponse: SignUpResponse) {
        context.showAlert(R.string.success, registerResponse.message) {
            activity.popFragment()
        }
    }

    private fun handleRegisterError(throwable: Throwable) {
        context.showAlert(R.string.error, (throwable as ApiException).errorMessage)
    }

    private fun handleProgressDialogStatus(status: Boolean) {
        if (status) {
            progressDialog.show()
        } else {
            progressDialog.dismiss()
        }
    }

    private fun handleValidateRegisterInformation(status: Boolean) {
        btnRegister.isEnabled = status
    }
}
