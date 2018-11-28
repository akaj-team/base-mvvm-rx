package com.android.asiantech.rx_mvvm_base.ui.main.home

import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

/**
 *
 * @author at-huongnguyen.
 */
interface HomeVMContract {

    /**
     * @return comic list
     */
    fun getComics(): MutableList<Comic>

    /**
     * Get comic list from server
     */
    fun getComicsFromServer()

    /**
     * @return observable when request success
     */
    fun getComicsObservable(): PublishSubject<MutableList<Comic>>

    /**
     * @return status show/hide progress loading
     */
    fun updateProgressDialogStatus(): BehaviorSubject<Boolean>

    /**
     * @param position is index clicked
     * @return status favorite
     */
    fun favorite(position: Int): Single<FavoriteResponse>

    /**
     * @param position is index clicked
     * @return status unFavorite
     */
    fun unFavorite(position: Int): Single<FavoriteResponse>

    /**
     * @param position is index clicked
     * @return status favorite
     */
    fun isFavorite(position: Int): Boolean

    /**
     * @param visibleItemCount is items visible on recyclerView
     * @param totalItemCount is total items on recyclerView
     * @param firstVisibleItem is first item visible on recyclerView
     */
    fun loadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int)

}
