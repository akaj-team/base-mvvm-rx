package com.android.asiantech.rx_mvvm_base.ui.comic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiException
import com.android.asiantech.rx_mvvm_base.extension.observeOnUiThread
import com.android.asiantech.rx_mvvm_base.extension.showAlert
import com.android.asiantech.rx_mvvm_base.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_comic_detail.*

class ComicDetailFragment : BaseFragment() {
    private lateinit var viewModel: ComicDetailVMContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Init viewModel
        viewModel = ComicDetailViewModel(Repository())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_comic_detail, container, false)
    }

    override fun onBindViewModel() {
        addDisposables(
                viewModel.onProgressBarStatus()
                        .observeOnUiThread()
                        .subscribe(this::onProgressBarStatus),
                viewModel.getComicDetail(1)
                        .observeOnUiThread()
                        .subscribe(this::updateUI, this::onErrorMessageStatus)
        )
    }

    private fun updateUI(comic: Comic) {
        tvComicName.text = comic.name
        tvAuthor.text = comic.author
        tvComicIntroduction.text = comic.introduction
    }

    private fun onProgressBarStatus(isShow: Boolean) {
        progressBar.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    private fun onErrorMessageStatus(error: Throwable) {
        context.showAlert(R.string.error, (error as ApiException).errorMessage)
    }
}
