package com.android.asiantech.rx_mvvm_base.data.source.datasource

import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.*
import io.reactivex.Single

/**
 *
 * @author at-vinhhuynh
 */
interface DataSource {

    /**
     * @param email is user email
     * @param password is user password
     * @return loginResponse contain access token
     */
    fun login(email: String, password: String): Single<LoginResponse>

    /**
     * Register new account
     * @param email is email used register
     * @param password is password used register
     * @return signUp status
     */
    fun register(email: String, password: String, avatar: String): Single<SignUpResponse>

    /**
     * Get list comic from server
     * @param page is specify the number of page
     * @return homeResponse contain list comic
     */
    fun getComics(page: Int): Single<HomeResponse>

    /**
     * @param id is comic id
     * @return favorite status
     */
    fun favorite(id: Int): Single<FavoriteResponse>

    /**
     * @param id is comic id
     * @return favorite status
     */
    fun unFavorite(id: Int): Single<FavoriteResponse>

    fun getComic(comicId: Int): Single<Comic>

    fun getProfile(): Single<User>

    fun getFavoriteMangaList(page: Int): Single<FavoriteDataResponse>

    fun getListFavorites(page: Int): Single<ListFavoritesResponse>
}
