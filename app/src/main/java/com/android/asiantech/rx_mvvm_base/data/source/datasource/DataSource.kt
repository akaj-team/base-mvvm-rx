package com.android.asiantech.rx_mvvm_base.data.source.datasource

import com.android.asiantech.rx_mvvm_base.data.model.Manga
import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.LoginResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ResultResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.SignUpResponse
import io.reactivex.Single

/**
 *
 * @author at-vinhhuynh
 */
interface DataSource {

    fun login(email: String, password: String): Single<LoginResponse>

    fun register(email: String, password: String, avatar: String): Single<SignUpResponse>

    // Profile data source

    fun getProfile(): Single<User>

    fun getMangaList(page: Int): Single<FavoriteResponse>

    fun star(id: Int): Single<ResultResponse>

    fun unStar(id: Int): Single<ResultResponse>

}
