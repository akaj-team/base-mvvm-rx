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

    private var currentPage = 1
    private val comics = mutableListOf<Comic>()
    private val progressDialogStatus: BehaviorSubject<Boolean> = BehaviorSubject.create()

    override fun getComics() = comics

    override fun getComicsFromServer(): Single<HomeResponse> {
        return repository.getComics(currentPage)
                .doOnSubscribe {
                    progressDialogStatus.onNext(true)
                }
                .doOnSuccess {
                    progressDialogStatus.onNext(false)
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
    }

    override fun updateProgressDialogStatus() = progressDialogStatus
}
