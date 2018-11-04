package com.android.asiantech.rx_mvvm_base.ui.profile

import com.android.asiantech.rx_mvvm_base.data.model.Manga
import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ResultResponse
import io.reactivex.Single

/**
 * @author ChauHQ
 */
interface ProfileContract {

    fun getProfile(): Single<User>

    fun getMangaList(): MutableList<Manga>

    fun getFavoriteMangaList(): Single<FavoriteResponse>

    fun updateFavorite(manga: Manga): Single<ResultResponse>

    fun loadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int) : Single<FavoriteResponse>?
}
