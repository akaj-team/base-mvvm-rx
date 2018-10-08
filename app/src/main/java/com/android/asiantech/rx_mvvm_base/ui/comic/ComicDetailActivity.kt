package com.android.asiantech.rx_mvvm_base.ui.comic

import android.os.Bundle
import android.view.View
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.source.LocalRepository
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiException
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ComicResponse
import com.android.asiantech.rx_mvvm_base.extension.observeOnUiThread
import com.android.asiantech.rx_mvvm_base.extension.showAlert
import com.android.asiantech.rx_mvvm_base.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_comic_detail.*

class ComicDetailActivity : BaseActivity() {

    private lateinit var viewModel: ComicDetailVMContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)
        init()
    }

    private fun init() {
        // Init viewModel
        viewModel = ComicDetailViewModel(Repository(), LocalRepository(this))
        viewModel.onProgressBarStatus()
                .observeOnUiThread()
                .subscribe(this::onProgressBarStatus)
        viewModel.getComicDetail(1)
                .observeOnUiThread()
                .subscribe(this::updateUI, this::onErrorMessageStatus)
    }

    private fun updateUI(comicResponse: ComicResponse) {
        tvComicName.text = comicResponse.name
        tvComicIntroduction.text = "Introduction: " + comicResponse.description
        tvAuthor.text = "Author: " + comicResponse.author
    }

    private fun onProgressBarStatus(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun onErrorMessageStatus(error: Throwable) {
        val apiException = error as ApiException
        showAlert(R.string.error, apiException.errorMessage)
    }
}
