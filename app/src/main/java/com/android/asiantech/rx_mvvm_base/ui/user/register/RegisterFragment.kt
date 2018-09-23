package com.android.asiantech.rx_mvvm_base.ui.user.register

import android.app.ProgressDialog
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.extension.popFragment
import com.android.asiantech.rx_mvvm_base.ui.base.BaseFragment
import com.android.asiantech.rx_mvvm_base.ui.user.UserActivity
import kotlinx.android.synthetic.main.fragment_register.*

/**
 *
 * @author at-vinhhuynh
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
                viewModel.progressDialogStatus().subscribe(this::handleProgressDialogStatus),
                viewModel.validateRegisterInformation().subscribe(this::handleValidateRegisterInformation)
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
            register()
        }
    }

    private fun register() {}

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
