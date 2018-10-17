package com.android.asiantech.rx_mvvm_base.data.source

import com.android.asiantech.rx_mvvm_base.data.model.Manga
import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.datasource.DataSource
import com.android.asiantech.rx_mvvm_base.data.source.remote.RemoteDataSource
import io.reactivex.Single

/**
 *
 * @author at-vinhhuynh
 */
class Repository : DataSource {

    private val remoteDataSource: RemoteDataSource = RemoteDataSource()

    override fun login(email: String, password: String) = remoteDataSource.login(email, password)

    override fun register(email: String, password: String, avatar: String) = remoteDataSource.register(email, password, avatar)

    override fun getProfile(): Single<User>

    override fun getMangaList(): Single<MutableList<Manga>> {}
}
