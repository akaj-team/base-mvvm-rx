package com.android.asiantech.rx_mvvm_base.ui.profile

import com.android.asiantech.rx_mvvm_base.data.model.Manga
import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteDataResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

/**
 * @author ChauHQ
 */
interface ProfileVMContract {

    /**
     * Get Profile
     * @return User data
     */
    fun getProfile(): Single<User>

    /**
     * Get manga list
     */
    fun getMangaList(): MutableList<Manga>

    /**
     * Get favorite manga list
     * @return favoriteDataResponse observable
     */
    fun getFavoriteMangaList(): Single<FavoriteDataResponse>

    /**
     * Get update favorite manga list
     * @return favoriteResponse observable
     */
    fun updateFavorite(position: Int): Single<FavoriteResponse>

    /**
     * Load more for favorite manga list
     * @return favoriteDataResponse observable
     */
    fun loadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int): Single<FavoriteDataResponse>?

    /**
     * Update state Progressbar
     */
    fun updateStateProgressBarObservable(): BehaviorSubject<Int>
}
