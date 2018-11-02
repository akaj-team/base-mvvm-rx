package com.android.asiantech.rx_mvvm_base.ui.user

import com.android.asiantech.rx_mvvm_base.data.source.LocalRepository

/**
 *
 * @author at-vinhhuynh
 */
class UserViewModel(private val localRepository: LocalRepository) : UserVMContract {

    override fun getAccessToken() = localRepository.getApiToken()
}
