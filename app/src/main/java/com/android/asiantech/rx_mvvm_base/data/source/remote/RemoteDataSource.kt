package com.android.asiantech.rx_mvvm_base.data.source.remote

import com.android.asiantech.rx_mvvm_base.data.model.Manga
import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.datasource.DataSource
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiClient
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiService
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ResultResponse
import io.reactivex.Single

/**
 *
 * @author at-vinhhuynh
 */
class RemoteDataSource(private val api: ApiService) : DataSource {

    constructor() : this(ApiClient.getInstance(null).service)

    override fun login(email: String, password: String) = api.login(email, password)

    override fun register(email: String, password: String, avatar: String) = api.register(email, password, avatar)

    override fun getProfile(): Single<User> = api.getProfile()

    override fun getMangaList(page: Int): Single<FavoriteResponse> = api.getFavorite(page)

    override fun star(id: Int): Single<ResultResponse> = api.star(id)

    override fun unStar(id: Int): Single<ResultResponse> = api.unStar(id)
}
