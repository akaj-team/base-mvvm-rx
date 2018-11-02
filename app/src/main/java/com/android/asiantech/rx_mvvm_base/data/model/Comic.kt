package com.android.asiantech.rx_mvvm_base.data.model

import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ComicResponse

class Comic(val name: String?, val author: String?, val introduction: String?) {
    constructor(comicResponse: ComicResponse) : this(comicResponse.name, comicResponse.author, comicResponse.description)
}
