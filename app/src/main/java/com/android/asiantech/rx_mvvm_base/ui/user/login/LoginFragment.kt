package com.android.asiantech.rx_mvvm_base.ui.user.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.source.LocalRepository
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiException
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.LoginResponse
import com.android.asiantech.rx_mvvm_base.extension.observeOnUiThread
import com.android.asiantech.rx_mvvm_base.extension.onTextChangeListener
import com.android.asiantech.rx_mvvm_base.extension.showAlert
import com.android.asiantech.rx_mvvm_base.ui.base.BaseFragment
import com.android.asiantech.rx_mvvm_base.ui.comic.ComicDetailActivity
import com.android.asiantech.rx_mvvm_base.ui.user.UserActivity
import kotlinx.android.synthetic.main.fragment_login.*

/**
 *
 * @author at-vinhhuynh
 */
class LoginFragment : BaseFragment() {

    private lateinit var viewModel: LoginVMContract
    private lateinit var progressDialog: ProgressDialog

    companion object {
        private const val REQUEST_CODE_MAIN = 2802
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = LoginViewModel(Repository(), LocalRepository(context))
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
                        .subscribe(this::handleValidateLoginInformation)
        )
    }

    private fun initListener() {
        tvSignUp.setOnClickListener {
            (activity as? UserActivity)?.openRegisterFragment()
        }

        btnLogin.setOnClickListener {
            login(edtEmail.text.toString(), edtPassword.text.toString())
        }

        edtEmail.onTextChangeListener {
            viewModel.validateLoginInformation(edtEmail.text.toString(),
                    edtPassword.text.toString())
        }

        edtPassword.onTextChangeListener {
            viewModel.validateLoginInformation(edtEmail.text.toString(),
                    edtPassword.text.toString())
        }
    }

    private fun initView() {
        progressDialog = ProgressDialog(context, R.style.AppTheme)
        progressDialog.setCancelable(false)
    }

    private fun handleProgressDialogStatus(status: Boolean) = if (status) progressDialog.show() else progressDialog.dismiss()

    private fun handleLoginSuccess(loginResponse: LoginResponse) {
        viewModel.saveApiToken(loginResponse.accessToken)
        ActivityCompat.startActivityForResult(activity, Intent(context, ComicDetailActivity::class.java), REQUEST_CODE_MAIN, null)
    }

    private fun handleLoginError(throwable: Throwable) {
        context.showAlert(R.string.error, (throwable as ApiException).errorMessage)
    }

    private fun handleValidateLoginInformation(status: Boolean) {
        btnLogin.isEnabled = status
    }


    @SuppressWarnings("CheckResult")
    private fun login(email: String, password: String) {
        viewModel.login(email, password)
                .observeOnUiThread()
                .subscribe(this::handleLoginSuccess, this::handleLoginError)
    }
}
