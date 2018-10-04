package com.android.asiantech.rx_mvvm_base.ui.comic

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.android.asiantech.rx_mvvm_base.extension.observeOnUiThread
import com.android.asiantech.rx_mvvm_base.extension.showAlert
import com.android.asiantech.rx_mvvm_base.ui.base.BaseActivity

class ComicDetailActivity : BaseActivity() {

    private lateinit var viewModel: ComicDetailVMContract
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)
        viewModel.onProgressBarStatus()
                .observeOnUiThread()
                .subscribe(this::onProgressBarStatus)
        viewModel.onErrorMessageStatus()
                .observeOnUiThread()
                .subscribe(this::onErrorMessageStatus)
        viewModel.getComicDetail(100).observeOnUiThread().subscribe { comic, error ->
            updateUI(comic)
            onErrorMessageStatus(error.message!!)
        }
    }

    private fun updateUI(comic: Comic) {
        // TODO:
    }

    private fun onProgressBarStatus(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun onErrorMessageStatus(message: String) {
        showAlert(R.string.error, message)
    }
}
