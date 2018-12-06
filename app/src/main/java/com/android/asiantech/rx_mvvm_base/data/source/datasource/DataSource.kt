package com.android.asiantech.rx_mvvm_base.data.source.datasource

import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ListFavoritesResponse
import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.HomeResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.LoginResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.SignUpResponse
import io.reactivex.Single
import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.*

/**
 *
 * @author at-vinhhuynh
 */
interface DataSource {

    fun login(email: String, password: String): Single<LoginResponse>

    fun register(email: String, password: String, avatar: String): Single<SignUpResponse>

    fun getComics(page: Int): Single<HomeResponse>

    fun favorite(id: Int): Single<FavoriteResponse>

    fun unFavorite(id: Int): Single<FavoriteResponse>

    fun getComic(comicId: Int): Single<Comic>

    fun getProfile(): Single<User>

    fun getFavoriteMangaList(page: Int): Single<FavoriteDataResponse>
    fun getListFavorites(page: Int): Single<ListFavoritesResponse>
}
