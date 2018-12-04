package com.android.asiantech.rx_mvvm_base.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author ChauHQ
 */
data class Manga(@SerializedName("id") val id: Int,
                 @SerializedName("name") val name: String,
                 @SerializedName("description") val description: String,
                 @SerializedName("image") val thumbnail: String,
                 @SerializedName("like_flag") var likeFlag: Boolean)
