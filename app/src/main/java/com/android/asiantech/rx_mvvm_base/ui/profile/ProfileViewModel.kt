package com.android.asiantech.rx_mvvm_base.ui.profile

import com.android.asiantech.rx_mvvm_base.data.model.Manga
import com.android.asiantech.rx_mvvm_base.data.model.User
import io.reactivex.Single

/**
 * @author ChauHQ
 */
class ProfileViewModel : ProfileContract {
    override fun loadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int): Single<MutableList<Manga>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getProfile(): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMangaList(): Single<MutableList<Manga>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateFavorite(): Single<Void> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}