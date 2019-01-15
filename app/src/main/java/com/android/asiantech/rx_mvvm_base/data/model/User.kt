package com.android.asiantech.rx_mvvm_base.data.model

import com.google.gson.annotations.SerializedName

/**
 *
 * @author at-vinhhuynh
 */
data class User(@SerializedName("avatar") val avatar: String,
                @SerializedName("user-name") val userName: String,
                @SerializedName("nick-name") val nickName: String,
                @SerializedName("rate-count") val rateCount: Int)
