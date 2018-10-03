package com.android.asiantech.rx_mvvm_base.data.source.datasource

/**
 *
 * @author at-vinhhuynh
 */
interface LocalDataSource {

    fun saveApiToken(apiToken: String)

    fun getApiToken(): String
}
