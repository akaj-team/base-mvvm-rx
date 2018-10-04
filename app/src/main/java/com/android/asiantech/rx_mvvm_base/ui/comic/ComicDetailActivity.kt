package com.android.asiantech.rx_mvvm_base.ui.comic

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiException
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.ComicResponse
import com.android.asiantech.rx_mvvm_base.extension.observeOnUiThread
import com.android.asiantech.rx_mvvm_base.extension.showAlert
import com.android.asiantech.rx_mvvm_base.ui.base.BaseActivity

class ComicDetailActivity : BaseActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var viewModel: ComicDetailVMContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)
        progressBar = findViewById(R.id.progressBar)
        viewModel = ComicDetailViewModel(Repository())
    }

    override fun onResume() {
        super.onResume()
        viewModel.onProgressBarStatus()
                .observeOnUiThread()
                .subscribe(this::onProgressBarStatus)
        viewModel.getComicDetail(100).observeOnUiThread().subscribe(this::updateUI, this::onErrorMessageStatus)
    }

    private fun updateUI(comicResponse: ComicResponse) {
        // TODO:
        Log.d("ComicDetailActivity", comicResponse.description)
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
