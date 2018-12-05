package com.android.asiantech.rx_mvvm_base.data.source.remote.response

import com.android.asiantech.rx_mvvm_base.data.model.Manga
import com.google.gson.annotations.SerializedName

/**
 *
 * @author at-huongnguyen
 */
class FavoriteResponse(@SerializedName("success") val success: Boolean)

data class FavoriteDataResponse(@SerializedName("next_page_flag") val nextPageFlag: Boolean,
                            @SerializedName("") val mangaList: List<Manga>)
