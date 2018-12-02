package com.android.asiantech.rx_mvvm_base.data.source.datasource

/**
 *
 * @author at-vinhhuynh
 */
interface LocalDataSource {

    /**
     * Save api token to shareReference
     */
    fun saveApiToken(apiToken: String)

    /**
     * Clear api token in shareReference
     */
    fun clearApiToken()

    /**
     * Get api token from shareReference
     */
    fun getApiToken(): String
}
