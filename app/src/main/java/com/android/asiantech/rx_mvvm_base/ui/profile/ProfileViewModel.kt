package com.android.asiantech.rx_mvvm_base.ui.profile

import com.android.asiantech.rx_mvvm_base.data.model.Manga
import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ResultResponse
import io.reactivex.Single

/**
 * @author ChauHQ
 */
class ProfileViewModel(private val repository: Repository) : ProfileContract {
    private val page = 1
    private val mangas = arrayListOf<Manga>()

    override fun getMangaList(): MutableList<Manga> = mangas

    override fun loadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int) {}

    override fun getProfile(): Single<User> = repository.getProfile()

    override fun getFavoriteMangaList(): Single<List<Manga>> = repository.getMangaList(page)

    override fun updateFavorite(manga: Manga): Single<ResultResponse> {
        if (manga.likeFlag) {
            return repository.unStar(manga.id).doOnSuccess {
                if (it.success) {
                    manga.likeFlag = false
                }
            }
        } else {
            return repository.star(manga.id).doOnSuccess {
                if (it.success) {
                    manga.likeFlag = true
                }
            }
        }
    }
}
