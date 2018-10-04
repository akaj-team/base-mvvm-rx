package com.android.asiantech.rx_mvvm_base.ui.comic

import com.android.asiantech.rx_mvvm_base.data.model.Comic
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

interface ComicDetailVMContract {
    fun getComicDetail(comicId: Int): Single<Comic>

    fun onProgressBarStatus(): BehaviorSubject<Boolean>

    fun onErrorMessageStatus(): BehaviorSubject<String>
}
