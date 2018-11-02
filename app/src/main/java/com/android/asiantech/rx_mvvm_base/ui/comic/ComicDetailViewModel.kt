package com.android.asiantech.rx_mvvm_base.ui.comic

import com.android.asiantech.rx_mvvm_base.data.source.LocalRepository
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiClient
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ComicResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class ComicDetailViewModel(private val repository: Repository) : ComicDetailVMContract {

    private val progressBarStatus = BehaviorSubject.create<Boolean>()

    override fun getComicDetail(comicId: Int): Single<ComicResponse> {
        return repository.getComic(comicId)
                .doOnSubscribe {
                    progressBarStatus.onNext(true)
                }.doFinally {
                    progressBarStatus.onNext(false)
                }
    }

    override fun onProgressBarStatus(): BehaviorSubject<Boolean> = progressBarStatus
}
