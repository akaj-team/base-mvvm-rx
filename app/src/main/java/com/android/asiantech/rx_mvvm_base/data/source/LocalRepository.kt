package com.android.asiantech.rx_mvvm_base.data.source

import android.content.Context
import com.android.asiantech.rx_mvvm_base.data.source.datasource.LocalDataSource

/**
 *
 * @author at-vinhhuynh
 */
class LocalRepository(context: Context) : LocalDataSource {

    private val localDataSource = com.android.asiantech.rx_mvvm_base.data.source.local.LocalDataSource(context)

    override fun saveApiToken(apiToken: String) = localDataSource.saveApiToken(apiToken)

    override fun getApiToken() = localDataSource.getApiToken()
}
