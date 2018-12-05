package com.android.asiantech.rx_mvvm_base.data.source.remote.response

import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.google.gson.annotations.SerializedName

/**
 * Copyright © 2018 AsianTech inc.
 * Created by TaiND on 10/31/18.
 */
class ListFavoritesResponse(@SerializedName("next_page_flag") val nextPageFlag: Boolean,
                            @SerializedName("result") val result: List<Comic>)
