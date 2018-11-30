package com.android.asiantech.rx_mvvm_base.ui.main.home

import android.annotation.SuppressLint
import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.extension.observeOnUiThread
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

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
    private val comicsObservable: PublishSubject<MutableList<Comic>> = PublishSubject.create()
    private val progressDialogStatus: BehaviorSubject<Boolean> = BehaviorSubject.create()

    override fun getComics() = comics

    @SuppressLint("CheckResult")
    override fun getComicsFromServer() {
        repository.getComics(currentPage)
                .observeOnUiThread()
                .doOnSubscribe {
                    progressDialogStatus.onNext(currentPage == 1)
                    isLoading = true
                }
                .doFinally {
                    progressDialogStatus.onNext(false)
                }
                .subscribe({
                    isLoading = false
                    nextPageFlag = it.nextPageFlag
                    if (currentPage == 1) {
                        comics.clear()
                    }
                    if (it.nextPageFlag) {
                        currentPage++
                    }
                    if (it.comics.isNotEmpty()) {
                        comics.addAll(it.comics)
                    }
                    comicsObservable.onNext(comics)
                }, {
                    comicsObservable.onError(it)
                })
    }

    override fun getComicsObservable() = comicsObservable

    override fun updateProgressDialogStatus() = progressDialogStatus

    override fun favorite(position: Int) = repository.favorite(comics[position].id)

    override fun unFavorite(position: Int) = repository.unFavorite(comics[position].id)

    override fun isFavorite(position: Int) = comics[position].likeFlag

    override fun loadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int) {
        if (canLoadMore(visibleItemCount, totalItemCount, firstVisibleItem)) {
            getComicsFromServer()
        }
    }

    private fun canLoadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int) =
            !isLoading && nextPageFlag && (visibleItemCount + firstVisibleItem + VISIBLE_THRESHOLD >= totalItemCount)
}
