package com.android.asiantech.rx_mvvm_base.ui.main.favorite

import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ListFavoritesResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

/**
 * Copyright © 2018 AsianTech inc.
 * Created by TaiND on 10/31/18.
 */
class FavoriteViewModel(private val repository: Repository) : FavoriteVMContract {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private val listFavoriteComics = mutableListOf<Comic>()
    private var currentPage = 1
    private var isLoading = false
    private var nextPageFlag = false
    private val progressDialogStatus: BehaviorSubject<Boolean> = BehaviorSubject.create()
    private val noResultStatus: BehaviorSubject<Boolean> = BehaviorSubject.create()

    override fun getFavoriteComics(): List<Comic> = listFavoriteComics

    override fun getFavoriteComicsFromServer(): Single<ListFavoritesResponse> {
        return repository.getListFavorites(currentPage)
                .doOnSubscribe {
                    progressDialogStatus.onNext(true)
                    isLoading = true
                }
                .doAfterSuccess {
                    isLoading = false
                    if (currentPage == 1) {
                        listFavoriteComics.clear()
                    }
                    if (it.nextPageFlag) {
                        currentPage++
                    }
                    if (it.result.isNotEmpty()) {
                        listFavoriteComics.addAll(it.result)
                    }
                    noResultStatus.onNext(listFavoriteComics.size == 0)
                }
                .doFinally {
                    progressDialogStatus.onNext(false)
                }
    }

    override fun progressDialogStatus() = progressDialogStatus

    override fun noResultStatus(): BehaviorSubject<Boolean> = noResultStatus

    override fun loadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int) {
        if (canLoadMore(visibleItemCount, totalItemCount, firstVisibleItem)) {
            getFavoriteComicsFromServer()
        }
    }

    private fun canLoadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int) =
            !isLoading && nextPageFlag && (visibleItemCount + firstVisibleItem + VISIBLE_THRESHOLD >= totalItemCount)
}
