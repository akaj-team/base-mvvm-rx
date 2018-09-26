package com.android.asiantech.rx_mvvm_base.data.source.remote

import com.android.asiantech.rx_mvvm_base.data.source.datasource.DataSource
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiClient
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiService

/**
 *
 * @author at-vinhhuynh
 */
class RemoteDataSource(private val api: ApiService) : DataSource {

    constructor() : this(ApiClient.getInstance(null).service)

    override fun login(email: String, password: String) = api.login(email, password)

    override fun register(email: String, password: String, avatar: String) = api.register(email, password, avatar)
}