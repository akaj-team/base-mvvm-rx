package com.android.asiantech.rx_mvvm_base.data.source.remote.response

import com.android.asiantech.rx_mvvm_base.data.model.Manga
import com.google.gson.annotations.SerializedName

/**
 * @author ChauHQ
 */
data class FavoriteResponse(@SerializedName("next_page_flag") val nextPageFlag: Boolean, @SerializedName("") val mangaList: List<Manga>)
