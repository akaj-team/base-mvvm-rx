package com.android.asiantech.rx_mvvm_base.ui.main.setting

import com.android.asiantech.rx_mvvm_base.data.source.LocalRepository

/**
 * Copyright © 2017 Asian Tech Co., Ltd.
 * Created by quocnguyenp. on 10/5/18.
 */
class SettingViewModel(private val localRepository: LocalRepository) : SettingVMContract {

    override fun clearApiToken() = localRepository.clearApiToken()
}
