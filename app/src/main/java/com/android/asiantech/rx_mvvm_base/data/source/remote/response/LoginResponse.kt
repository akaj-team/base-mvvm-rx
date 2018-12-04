package com.android.asiantech.rx_mvvm_base.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 *
 * @author at-haingo
 */
class LoginResponse(@SerializedName("access_token") val accessToken: String)
