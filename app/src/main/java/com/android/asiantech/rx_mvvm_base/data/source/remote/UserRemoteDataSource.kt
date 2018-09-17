package com.android.asiantech.rx_mvvm_base.data.source.remote

import com.android.asiantech.rx_mvvm_base.data.source.datasource.UserDataSource
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiClient
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiService

/**
 *
 * @author at-vinhhuynh
 */
class UserRemoteDataSource(private val api: ApiService) : UserDataSource {

    constructor() : this(ApiClient.getInstance(null).service)

    override fun login() = api.login()
}
