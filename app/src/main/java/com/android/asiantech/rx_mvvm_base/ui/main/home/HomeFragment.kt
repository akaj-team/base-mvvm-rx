package com.android.asiantech.rx_mvvm_base.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.ui.base.BaseFragment

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 10/4/18.
 */
class HomeFragment : BaseFragment() {

    override fun onBindViewModel() {
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }
}
