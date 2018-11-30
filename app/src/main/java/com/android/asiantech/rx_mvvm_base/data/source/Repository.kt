package com.android.asiantech.rx_mvvm_base.data.source

import com.android.asiantech.rx_mvvm_base.data.source.datasource.DataSource
import com.android.asiantech.rx_mvvm_base.data.source.remote.RemoteDataSource

/**
 *
 * @author at-vinhhuynh
 */
class Repository : DataSource {

    private val remoteDataSource: RemoteDataSource = RemoteDataSource()

    override fun login(email: String, password: String) = remoteDataSource.login(email, password)

    override fun register(email: String, password: String, avatar: String) = remoteDataSource.register(email, password, avatar)

    override fun getComics(page: Int) = remoteDataSource.getComics(page)

    override fun favorite(id: Int) = remoteDataSource.favorite(id)

    override fun unFavorite(id: Int) = remoteDataSource.unFavorite(id)
}
