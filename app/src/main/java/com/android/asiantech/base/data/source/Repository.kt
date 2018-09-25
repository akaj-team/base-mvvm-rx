package com.android.asiantech.base.data.source

import com.android.asiantech.base.data.source.datasource.DataSource
import com.android.asiantech.base.data.source.remote.RemoteDataSource

/**
 *
 * @author at-vinhhuynh
 */
class Repository : DataSource {

    private val remoteDataSource: RemoteDataSource = RemoteDataSource()

    override fun login() = remoteDataSource.login()

}
