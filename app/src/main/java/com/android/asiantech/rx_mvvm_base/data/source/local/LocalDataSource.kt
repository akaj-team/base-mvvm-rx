package com.android.asiantech.rx_mvvm_base.data.source.local

import com.android.asiantech.rx_mvvm_base.data.source.datasource.DataSource
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiClient
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiService

/**
 *
 * @author at-vinhhuynh
 */
class LocalDataSource(private val api: ApiService) : DataSource {

    constructor() : this(ApiClient.getInstance(null).service)

    override fun login() = api.login()
}
