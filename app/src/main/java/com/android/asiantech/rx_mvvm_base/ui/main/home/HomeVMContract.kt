package com.android.asiantech.rx_mvvm_base.ui.main.home

import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.HomeResponse
import io.reactivex.Notification
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

/**
 *
 * @author at-huongnguyen.
 */
interface HomeVMContract {
    /**
     * Get comic items to display in view
     */
    fun getComics(): MutableList<Comic>

    /**
     * Get comic list from server
     */
    fun getComicsFromServer()

    /**
     * Observable get list comics
     */
    fun getComicsObservable(): PublishSubject<MutableList<Comic>>

    /**
     *
     * Update progress bar
     */
    fun updateProgressDialogStatus(): BehaviorSubject<Boolean>

    /**
     * Favorite
     */
    fun favorite(position: Int): Single<FavoriteResponse>

    /**
     * UunFavorite
     */
    fun unFavorite(position: Int): Single<FavoriteResponse>

    /**
     * Check favorite
     */
    fun isFavorite(position: Int): Boolean

    /**
     * Handle load more
     */
    fun loadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int)

}
