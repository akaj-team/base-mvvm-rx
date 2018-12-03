package com.android.asiantech.rx_mvvm_base.data.source.datasource

/**
 *
 * @author at-vinhhuynh
 */
interface LocalDataSource {

    /**
     * Save api token to shareReference
     * @param apiToken is token get from server after login
     */
    fun saveApiToken(apiToken: String)

    /**
     * Clear api token in shareReference
     */
    fun clearApiToken()

    /**
     * Get api token from shareReference
     * @return api token
     */
    fun getApiToken(): String
}
