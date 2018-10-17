package com.android.asiantech.rx_mvvm_base.ui.profile

import com.android.asiantech.rx_mvvm_base.data.model.Manga
import com.android.asiantech.rx_mvvm_base.data.model.User
import io.reactivex.Single

/**
 * @author ChauHQ
 */
interface ProfileContract {

    fun getProfile(): Single<User>

    fun getMangaList(): Single<MutableList<Manga>>

    fun updateFavorite(): Single<Void>

    fun loadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int): Single<MutableList<Manga>>
}
