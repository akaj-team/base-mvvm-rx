package com.android.asiantech.rx_mvvm_base.ui.main.favorite

import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ListFavoritesResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

/**
 * Copyright © 2018 AsianTech inc.
 * Created by TaiND on 10/31/18.
 */
interface FavoriteContract {
    fun getFavoriteComics(): List<Comic>

    fun getFavoriteComicsFromServer(): Single<ListFavoritesResponse>

    fun progressDialogState(): BehaviorSubject<Boolean>

    fun loadMore(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int)
}
