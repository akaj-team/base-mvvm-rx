package com.android.asiantech.rx_mvvm_base.data.source.remote.response

data class ComicResponse(val id: Int = 0, val name: String? = null, val description: String? = null,
                         val author: String? = null, val viewCount: Int = 0, val likeCount: Int = 0,
                         val imageUrl: String? = null)
