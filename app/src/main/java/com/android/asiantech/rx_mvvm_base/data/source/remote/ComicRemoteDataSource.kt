package com.android.asiantech.rx_mvvm_base.data.source.remote

import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.datasource.ComicDataSource
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiClient
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiService
import io.reactivex.Observable

/**
 *
 * @author at-vinhhuynh
 */
class ComicRemoteDataSource(private val api: ApiService) : ComicDataSource {

    constructor() : this(ApiClient.getInstance(null).service)

    override fun login(email: String, password: String) = api.login(email, password)

    override fun register(email: String, password: String, avatar: String) = api.register(email, password, avatar)

    override fun getComics(page: Int) = api.getComics(page)

    override fun favorite(id: Int) = api.favorite(id)

    override fun unFavorite(id: Int) = api.unFavorite(id)

    override fun getUser(): Observable<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
