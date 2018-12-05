package com.android.asiantech.rx_mvvm_base.data.model

import com.google.gson.annotations.SerializedName

/**
 *
 * @author at-huongnguyen
 */
data class Comic(@SerializedName("id") val id: Int,
                 @SerializedName("name") val name: String,
                 @SerializedName("description") val description: String,
                 @SerializedName("author") val author: String,
                 @SerializedName("view_count") val viewCount: Int,
                 @SerializedName("like_flag") var likeFlag: Boolean,
                 @SerializedName("like_count") val likeCount: Int,
                 @SerializedName("image") val image: String) {

}
