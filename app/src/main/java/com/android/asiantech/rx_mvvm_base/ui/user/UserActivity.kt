package com.android.asiantech.rx_mvvm_base.ui.user

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.source.LocalRepository
import com.android.asiantech.rx_mvvm_base.extension.addFragment
import com.android.asiantech.rx_mvvm_base.extension.observeOnUiThread
import com.android.asiantech.rx_mvvm_base.extension.replaceFragment
import com.android.asiantech.rx_mvvm_base.ui.base.BaseActivity
import com.android.asiantech.rx_mvvm_base.ui.main.MainActivity
import com.android.asiantech.rx_mvvm_base.ui.user.login.LoginFragment
import com.android.asiantech.rx_mvvm_base.ui.user.register.RegisterFragment
import com.android.asiantech.rx_mvvm_base.extension.animSlideInRightSlideOutRight

/**
 *
 * @author at-haingo
 */
class UserActivity : BaseActivity() {
    private lateinit var viewModel: UserVMContract

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        viewModel = UserViewModel(LocalRepository(this))

        viewModel.loginStatus()
                .observeOnUiThread()
                .subscribe(this::handleCheckLogin)

        viewModel.checkLogin()
    }

    internal fun openRegisterFragment() {
        addFragment(R.id.userActivityContainer, RegisterFragment.newInstance(), {
            it.animSlideInRightSlideOutRight()
        }, RegisterFragment::class.java.simpleName, RegisterFragment::class.java.simpleName)
    }

    private fun openLoginFragment() {
        replaceFragment(R.id.userActivityContainer, LoginFragment.newInstance(), {
            it.animSlideInRightSlideOutRight()
        })
    }

    private fun handleCheckLogin(isLoggedIn: Boolean) {
        if (isLoggedIn) {
            startActivityForResult(Intent(this, MainActivity::class.java), LoginFragment.REQUEST_CODE_MAIN)
            finish()
        } else {
            openLoginFragment()
        }
    }
}
