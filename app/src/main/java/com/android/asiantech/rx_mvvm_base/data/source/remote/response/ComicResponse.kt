package com.android.asiantech.rx_mvvm_base.data.source.remote.response

import com.android.asiantech.rx_mvvm_base.data.model.Comic

class ComicResponse {
    var id: Int = 0
    var name: String? = null
    var description: String? = null
    var author: String? = null
    var viewCount: Int = 0
    var likeCount: Int = 0
    var imageUrl: String? = null

    fun convertToComic(): Comic {
        return Comic(description, author, 0, imageUrl)
    }
}
