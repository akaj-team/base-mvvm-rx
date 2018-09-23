package com.android.asiantech.rx_mvvm_base.data.source.remote.network

/**
 *
 * @author at-vinhhuynh.
 */
interface ApiService {

    /**
     * This method used to call image search by image file.
     */
    fun login()

    fun register(email: String, password: String)
}
