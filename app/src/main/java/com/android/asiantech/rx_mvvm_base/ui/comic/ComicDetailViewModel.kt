package com.android.asiantech.rx_mvvm_base.ui.comic

import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class ComicDetailViewModel(private val repository: Repository) : ComicDetailVMContract {

    private val progressBarStatus = BehaviorSubject.create<Boolean>()

    override fun getComicDetail(comicId: Int): Single<Comic> = repository.getComic(comicId)
            .doOnSubscribe{
                progressBarStatus.onNext(true)
            }.doFinally {
                progressBarStatus.onNext(false)
            }

    override fun onProgressBarStatus(): BehaviorSubject<Boolean> = progressBarStatus

    override fun onErrorMessageStatus(): BehaviorSubject<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
