package com.android.asiantech.rx_mvvm_base.data.source

import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.datasource.ComicDataSource
import com.android.asiantech.rx_mvvm_base.data.source.remote.ComicRemoteDataSource
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.HomeResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.LoginResponse
import io.reactivex.Observable
import io.reactivex.functions.BiFunction


/**
 *
 * @author at-vinhhuynh
 */
class ComicRepository : ComicDataSource {

    private val comicRemoteDataSource: ComicRemoteDataSource = ComicRemoteDataSource()

    override fun login(email: String, password: String) = comicRemoteDataSource.login(email, password)

    override fun register(email: String, password: String, avatar: String) = comicRemoteDataSource.register(email, password, avatar)

    override fun getComics(page: Int) = comicRemoteDataSource.getComics(page)

    override fun favorite(id: Int) = comicRemoteDataSource.favorite(id)

    override fun unFavorite(id: Int) = comicRemoteDataSource.unFavorite(id)

    override fun getUser(): Observable<User> {
        return Observable.zip(comicRemoteDataSource.login("email", "pass").toObservable(),
                comicRemoteDataSource.getComics(1).toObservable(),
                BiFunction<LoginResponse, HomeResponse, User> { t1, t2 ->
                    User(t1.accessToken, t2.comics[0].id)
                })
    }
}
