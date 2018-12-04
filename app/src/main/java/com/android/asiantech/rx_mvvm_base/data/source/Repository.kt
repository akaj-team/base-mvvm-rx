package com.android.asiantech.rx_mvvm_base.data.source

import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.datasource.DataSource
import com.android.asiantech.rx_mvvm_base.data.source.remote.RemoteDataSource
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.HomeResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.LoginResponse
import io.reactivex.Observable
import io.reactivex.functions.BiFunction


/**
 *
 * @author at-haingo
 */
class Repository : DataSource {

    private val remoteDataSource: RemoteDataSource = RemoteDataSource()

    override fun login(email: String, password: String) = remoteDataSource.login(email, password)

    override fun register(email: String, password: String, avatar: String) = remoteDataSource.register(email, password, avatar)

    override fun getComics(page: Int) = remoteDataSource.getComics(page)

    override fun favorite(id: Int) = remoteDataSource.favorite(id)

    override fun unFavorite(id: Int) = remoteDataSource.unFavorite(id)

    override fun getUser(): Observable<User> {
        return Observable.zip(remoteDataSource.login("email", "pass").toObservable(),
                remoteDataSource.getComics(1).toObservable(),
                BiFunction<LoginResponse, HomeResponse, User> { t1, t2 ->
                    User(t1.accessToken, t2.comics[0].id)
                })
    }
}
