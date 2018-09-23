package com.android.asiantech.rx_mvvm_base.ui.user

import android.os.Bundle
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.extension.addFragment
import com.android.asiantech.rx_mvvm_base.extension.replaceFragment
import com.android.asiantech.rx_mvvm_base.ui.base.BaseActivity
import com.android.asiantech.rx_mvvm_base.ui.user.login.LoginFragment
import com.android.asiantech.rx_mvvm_base.ui.user.register.RegisterFragment
import com.uniqlo.circle.extension.animSlideInRightSlideOutRight

/**
 *
 * @author at-vinhhuynh
 */
class UserActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        openLoginFragment()
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
}
