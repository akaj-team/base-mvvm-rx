package com.android.asiantech.rx_mvvm_base.ui.main.home

import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.HomeResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

/**
 *
 * @author at-huongnguyen.
 */
interface HomeVMContract {
    /**
     * Get comic items to display in view
     */
    fun getComics(): MutableList<Comic>

    /**
     * Get comic list from server
     */
    fun getComicsFromServer(): Single<HomeResponse>

    /**
     *
     * Update progress bar
     */
    fun updateProgressDialogStatus(): BehaviorSubject<Boolean>

}
