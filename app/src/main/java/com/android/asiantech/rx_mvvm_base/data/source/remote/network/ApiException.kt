package com.android.asiantech.rx_mvvm_base.data.source.remote.network

import com.google.gson.annotations.SerializedName

/**
 * Use this file to handle error from api
 * @author at-haingo
 */
data class ApiException(@SerializedName("message") val errorMessage: String,
                        @SerializedName("code") val code: Int) : Throwable(errorMessage)

