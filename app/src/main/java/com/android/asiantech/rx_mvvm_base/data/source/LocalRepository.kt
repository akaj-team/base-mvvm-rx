package com.android.asiantech.rx_mvvm_base.data.source

import android.content.Context
import com.android.asiantech.rx_mvvm_base.data.source.datasource.LocalDataSource
import com.android.asiantech.rx_mvvm_base.data.source.local.ComicLocalDataSource

/**
 *
 * @author at-haingo
 */
class LocalRepository(context: Context) : LocalDataSource {

    private val localDataSource = ComicLocalDataSource(context)

    override fun saveApiToken(apiToken: String) = localDataSource.saveApiToken(apiToken)

    override fun clearApiToken() = localDataSource.clearApiToken()

    override fun getApiToken() = localDataSource.getApiToken()
}
