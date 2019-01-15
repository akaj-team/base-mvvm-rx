package com.android.asiantech.rx_mvvm_base.data.source.local

import android.content.Context
import com.android.asiantech.rx_mvvm_base.BuildConfig
import com.android.asiantech.rx_mvvm_base.data.source.datasource.LocalDataSource

/**
 *
 * @author at-haingo
 */
class ComicLocalDataSource(private val context: Context) : LocalDataSource {

    companion object {
        private const val KEY_API_TOKEN = "KEY_API_TOKEN"
    }

    private val pref by lazy {
        context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

    override fun saveApiToken(apiToken: String) {
        pref.edit().putString(KEY_API_TOKEN, apiToken).apply()
    }

    override fun getApiToken(): String = pref.getString(KEY_API_TOKEN, "")

    override fun clearApiToken() {
        pref.edit().putString(KEY_API_TOKEN, "").apply()
    }
}
