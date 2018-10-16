package com.android.asiantech.rx_mvvm_base.ui.main.home

import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.HomeResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

/**
 * @author at-huongnguyen
 */
class HomeViewModel(private val repository: Repository) : HomeVMContract {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private var isLoading = false
    private var nextPageFlag = false
    private var currentPage = 1
    private val comics = mutableListOf<Comic>()
    private val progressDialogStatus: BehaviorSubject<Boolean> = BehaviorSubject.create()

    override fun getComics() = comics

    override fun getComicsFromServer(): Single<HomeResponse> {
        return repository.getComics(currentPage)
                .doOnSubscribe {
                    progressDialogStatus.onNext(true)
                    isLoading = true
                }
                .doOnSuccess {
                    isLoading = false
                    if (currentPage == 1) {
                        comics.clear()
                    }
                    if (it.nextPageFlag) {
                        currentPage++
                    }
                    if (it.comics.isNotEmpty()) {
                        comics.addAll(it.comics)
                    }
                }
                .doFinally {
                    progressDialogStatus.onNext(false)
                }
    }

    override fun updateProgressDialogStatus() = progressDialogStatus

    override fun favorite(position: Int) = repository.favorite(comics[position].id)

    override fun unFavorite(position: Int) = repository.unFavorite(comics[position].id)

    override fun isFavorite(position: Int) = comics[position].viewCount > 0

    override fun loadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int) {
        if (canLoadMore(visibleItemCount, totalItemCount, firstVisibleItem)) {
            getComicsFromServer()
        }
    }

    private fun canLoadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int) =
            !isLoading && nextPageFlag && (visibleItemCount + firstVisibleItem + VISIBLE_THRESHOLD >= totalItemCount)
}
