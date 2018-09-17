package com.android.asiantech.rx_mvvm_base.data.source

import com.android.asiantech.rx_mvvm_base.data.source.datasource.UserDataSource
import com.android.asiantech.rx_mvvm_base.data.source.remote.UserRemoteDataSource

/**
 *
 * @author at-vinhhuynh
 */
class UserRepository : UserDataSource {

    private val userRemoteDataSource: UserRemoteDataSource = UserRemoteDataSource()

    override fun login() = userRemoteDataSource.login()

}
