package com.android.asiantech.rx_mvvm_base.data.source.remote.response

import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.google.gson.annotations.SerializedName

/**
 *
 * @author at-huongnguyen
 */
class HomeResponse(@SerializedName("next_page_flag") val nextPageFlag: Boolean,
                   @SerializedName("result") val comics: MutableList<Comic>)
