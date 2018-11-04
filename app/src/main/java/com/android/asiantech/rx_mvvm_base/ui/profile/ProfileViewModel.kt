package com.android.asiantech.rx_mvvm_base.ui.profile

import com.android.asiantech.rx_mvvm_base.data.model.Manga
import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ResultResponse
import io.reactivex.Single

/**
 * @author ChauHQ
 */
class ProfileViewModel(private val repository: Repository) : ProfileContract {
    private var page = 1
    private val mangas = arrayListOf<Manga>()
    private var isLoading = false
    private var nextPageFlag = false

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    override fun getMangaList(): MutableList<Manga> = mangas

    override fun loadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int) : Single<FavoriteResponse>? {
        if (canLoadMore(visibleItemCount, totalItemCount, firstVisibleItem)) {
            return getFavoriteMangaList()
        }
        return null
    }

    override fun getProfile(): Single<User> = repository.getProfile()

    override fun getFavoriteMangaList(): Single<FavoriteResponse> = repository.getMangaList(page)
            .doOnSubscribe { isLoading = true }
            .doOnSuccess {
                isLoading = false
                nextPageFlag = it.nextPageFlag
                if (page == 1) {
                    mangas.clear()
                }
                page++
                mangas.addAll(it.mangaList)
            }

    override fun updateFavorite(manga: Manga): Single<ResultResponse> {
        return if (manga.likeFlag) {
            repository.unStar(manga.id).doOnSuccess {
                if (it.success) {
                    manga.likeFlag = false
                }
            }
        } else {
            repository.star(manga.id).doOnSuccess {
                if (it.success) {
                    manga.likeFlag = true
                }
            }
        }
    }

    private fun canLoadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int): Boolean {
        return !isLoading && nextPageFlag && (visibleItemCount + firstVisibleItem + VISIBLE_THRESHOLD >= totalItemCount)
    }
}
