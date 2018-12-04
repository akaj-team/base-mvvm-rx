package com.android.asiantech.rx_mvvm_base.ui.profile

import android.view.View
import com.android.asiantech.rx_mvvm_base.data.model.Manga
import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteDataResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ResultResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author ChauHQ
 */
class ProfileViewModel(private val repository: Repository) : ProfileVMContract {

    private var page = 1
    private val mangas = mutableListOf<Manga>()
    private var isLoading = false
    private var nextPageFlag = false
    private val updateStateProgressBarObservable = BehaviorSubject.create<Int>()
    private var count = AtomicInteger()

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    override fun getMangaList(): MutableList<Manga> = mangas

    override fun updateStateProgressBarObservable(): BehaviorSubject<Int> = updateStateProgressBarObservable

    override fun loadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int): Single<FavoriteDataResponse>? {
        if (canLoadMore(visibleItemCount, totalItemCount, firstVisibleItem)) {
            return getFavoriteMangaList()
        }
        return null
    }

    override fun getProfile(): Single<User> = repository.getProfile()
            .doOnSubscribe {
                count.incrementAndGet()
                updateStateProgressBarObservable.onNext(View.VISIBLE)
            }
            .doFinally {
                count.decrementAndGet()
                if (isAllowHideProgressbar()) {
                    updateStateProgressBarObservable.onNext(View.GONE)
                }
            }

    override fun getFavoriteMangaList(): Single<FavoriteDataResponse> = repository.getFavoriteMangaList(page)
            .doOnSubscribe {
                count.incrementAndGet()
                updateStateProgressBarObservable.onNext(View.VISIBLE)
            }
            .doFinally {
                count.decrementAndGet()
                if (isAllowHideProgressbar()) {
                    updateStateProgressBarObservable.onNext(View.GONE)
                }
            }
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

    override fun updateFavorite(position: Int): Single<ResultResponse> {
        val manga = mangas[position]
        return if (manga.likeFlag) {
            repository.unStar(manga.id)
                    .doOnSuccess {
                        if (it.success) {
                            manga.likeFlag = false
                        }
                    }
        } else {
            repository.star(manga.id)
                    .doOnSuccess {
                        if (it.success) {
                            manga.likeFlag = true
                        }
                    }
        }
    }

    private fun isAllowHideProgressbar() = count.get() == 0

    private fun canLoadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int): Boolean {
        return !isLoading && nextPageFlag && (visibleItemCount + firstVisibleItem + VISIBLE_THRESHOLD >= totalItemCount)
    }
}
