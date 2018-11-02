package com.android.asiantech.rx_mvvm_base.data.source.datasource

import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ComicResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.LoginResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.SignUpResponse
import io.reactivex.Single

/**
 *
 * @author at-vinhhuynh
 */
interface DataSource {

    fun login(email: String, password: String): Single<LoginResponse>

    fun register(email: String, password: String, avatar: String): Single<SignUpResponse>

    fun getComic(comicId: Int) : Single<Comic>
}
