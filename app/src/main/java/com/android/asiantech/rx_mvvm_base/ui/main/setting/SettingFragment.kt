package com.android.asiantech.rx_mvvm_base.ui.main.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.source.LocalRepository
import com.android.asiantech.rx_mvvm_base.ui.base.BaseFragment
import com.android.asiantech.rx_mvvm_base.ui.main.home.HomeFragment
import com.android.asiantech.rx_mvvm_base.ui.user.UserActivity
import com.uniqlo.circle.extension.replaceFragmentSafely
import kotlinx.android.synthetic.main.fragment_setting.*

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 10/4/18.
 */
class SettingFragment : BaseFragment() {

    private lateinit var viewModel: SettingViewModel

    override fun onBindViewModel() {
        // no-op
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = SettingViewModel(LocalRepository(context))
        initListener()
    }

    private fun initListener() {
        tvBtnLogout.setOnClickListener { logout() }

        tvBtnProfile.setOnClickListener { switchProfile() }
    }

    private fun switchProfile() {
        //todo implement later change screen
        replaceFragmentSafely(
                fragment = HomeFragment(),
                tag = "ProfileFragment",
                containerViewId = R.id.frSettingContainer,
                allowStateLoss = true
        )
    }

    private fun logout() {
        viewModel.clearApiToken()
        startActivity(Intent(context, UserActivity::class.java))
    }
}
