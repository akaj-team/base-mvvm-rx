package com.android.asiantech.rx_mvvm_base.ui.user.login

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.ui.base.BaseFragment
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
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = LoginViewModel(Repository())
        initView()
        initListener()
    }

    override fun onBindViewModel() {
        addDisposables(
                viewModel.progressDialogStatus().subscribe(this::handleProgressDialogStatus),
                viewModel.validateLoginInformation().subscribe(this::handleValidateLoginInformation)
        )
    }

    private fun initListener() {
        tvSignUp.setOnClickListener {
            (activity as? UserActivity)?.openRegisterFragment()
        }

        btnLogin.setOnClickListener {
        }
    }

    private fun initView() {
        progressDialog = ProgressDialog(context)
        progressDialog.setCancelable(false)
    }


    private fun handleProgressDialogStatus(status: Boolean) {
        if (status) {
            progressDialog.show()
        } else {
            progressDialog.dismiss()
        }
    }

    private fun handleValidateLoginInformation(status: Boolean) {
        btnLogin.isEnabled = status
    }

}
