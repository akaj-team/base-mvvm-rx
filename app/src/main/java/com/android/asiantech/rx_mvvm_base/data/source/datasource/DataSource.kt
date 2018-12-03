package com.android.asiantech.rx_mvvm_base.data.source.datasource

import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.HomeResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.LoginResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.SignUpResponse
import io.reactivex.Observable
import io.reactivex.Single

/**
 *
 * @author at-vinhhuynh
 */
interface DataSource {

    /**
     * Login
     */
    fun login(email: String, password: String): Single<LoginResponse>

    /**
     * Register new account
     */
    fun register(email: String, password: String, avatar: String): Single<SignUpResponse>

    /**
     * Get list comic from server
     */
    fun getComics(page: Int): Single<HomeResponse>

    /**
     * Like comic
     */
    fun favorite(id: Int): Single<FavoriteResponse>

    /**
     * Unlike comic
     */
    fun unFavorite(id: Int): Single<FavoriteResponse>

    /**email
     * Get user information
     */
    fun getUser(): Observable<User>
}
