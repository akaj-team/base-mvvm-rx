package com.android.asiantech.rx_mvvm_base.ui.user.register

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.extension.popFragment
import com.android.asiantech.rx_mvvm_base.ui.base.BaseFragment
import com.android.asiantech.rx_mvvm_base.ui.user.UserActivity
import kotlinx.android.synthetic.main.fragment_register.*

/**
 *
 * @author at-vinhhuynh
 */
class RegisterFragment : BaseFragment() {

    companion object {
        internal fun newInstance() = RegisterFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initListener()
    }

    override fun onBindViewModel() {
    }

    private fun initUI() {
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
}
