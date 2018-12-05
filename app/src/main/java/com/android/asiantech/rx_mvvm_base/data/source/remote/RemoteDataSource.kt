package com.android.asiantech.rx_mvvm_base.data.source.remote

import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.android.asiantech.rx_mvvm_base.data.source.datasource.DataSource
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiClient
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiService
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ListFavoritesResponse
import io.reactivex.Single
import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteDataResponse

/**
 *
 * @author at-vinhhuynh
 */
class RemoteDataSource(private val api: ApiService) : DataSource {

    constructor() : this(ApiClient.getInstance(null).service)

    override fun login(email: String, password: String) = api.login(email, password)

    override fun register(email: String, password: String, avatar: String) = api.register(email, password, avatar)

    override fun getComics(page: Int) = api.getComics(page)

    override fun favorite(id: Int) = api.favorite(id)

    override fun unFavorite(id: Int) = api.unFavorite(id)

    override fun getComic(comicId: Int): Single<Comic> = api.getComic(comicId)

    override fun getProfile(): Single<User> = api.getProfile()

    override fun getFavoriteMangaList(page: Int): Single<FavoriteDataResponse> = api.getFavorite(page)

    override fun getListFavorites(page: Int): Single<ListFavoritesResponse> = api.getListFavorites(page)
}
