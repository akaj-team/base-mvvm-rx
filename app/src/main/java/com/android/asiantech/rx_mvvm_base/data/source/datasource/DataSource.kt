package com.android.asiantech.rx_mvvm_base.data.source.datasource

/**
 *
 * @author at-vinhhuynh
 */
interface DataSource {

    fun login()

    fun register(email: String, password: String)
}
