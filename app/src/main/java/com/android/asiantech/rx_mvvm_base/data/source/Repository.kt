package com.android.asiantech.rx_mvvm_base.data.source

import com.android.asiantech.rx_mvvm_base.data.source.datasource.DataSource
import com.android.asiantech.rx_mvvm_base.data.source.remote.RemoteDataSource

/**
 *
 * @author at-vinhhuynh
 */
class Repository : DataSource {

    private val remoteDataSource: RemoteDataSource = RemoteDataSource()

    override fun login() = remoteDataSource.login()

    override fun register(email: String, password: String) = remoteDataSource.register(email, password)
}
