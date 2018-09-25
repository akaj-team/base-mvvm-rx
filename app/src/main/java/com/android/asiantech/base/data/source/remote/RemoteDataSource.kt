package com.android.asiantech.base.data.source.remote

import com.android.asiantech.base.data.source.datasource.DataSource
import com.android.asiantech.base.data.source.remote.network.ApiClient
import com.android.asiantech.base.data.source.remote.network.ApiService

/**
 *
 * @author at-vinhhuynh
 */
class RemoteDataSource(private val api: ApiService) : DataSource {

    constructor() : this(ApiClient.getInstance(null).service)

    override fun login() = api.login()
}
