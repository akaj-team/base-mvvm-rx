package com.android.asiantech.rx_mvvm_base.ui.main.favorite

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiException
import com.android.asiantech.rx_mvvm_base.extension.observeOnUiThread
import com.android.asiantech.rx_mvvm_base.extension.showAlert
import com.android.asiantech.rx_mvvm_base.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 10/4/18.
 */
class FavoriteFragment : BaseFragment() {

    private lateinit var viewModel: FavoriteContract
    private lateinit var adapter: FavoriteAdapter
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = FavoriteViewModel(Repository())
        adapter = FavoriteAdapter(viewModel.getFavoriteComics())
        adapter.onItemClicked = this::handleItemClicked
        return inflater?.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProgressDialog()
        initRecyclerView()
    }

    override fun onBindViewModel() {
        addDisposables(
                viewModel.progressDialogState()
                        .observeOnUiThread().subscribe(this::handleProgressDialogState),
                viewModel.getFavoriteComicsFromServer()
                        .observeOnUiThread()
                        .subscribe({ handleGetFavoriteComicsFromServerSuccess() }, this::handleGetFavoriteComicsError)
        )
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(context)
        progressDialog.setCancelable(false)
    }

    private fun initRecyclerView() {
        val gridLayoutManager = GridLayoutManager(context, 2)
        recyclerFavorites.apply {
            layoutManager = gridLayoutManager
            adapter = this@FavoriteFragment.adapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 5) {
                        val visibleItemCount = gridLayoutManager.childCount
                        val totalItemCount = gridLayoutManager.itemCount
                        val firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition()
                        viewModel.loadMore(visibleItemCount, totalItemCount, firstVisibleItem)
                    }
                }
            })
        }
    }

    private fun handleProgressDialogState(isShow: Boolean) {
        progressDialog.apply { if (isShow) show() else dismiss() }
    }

    private fun handleGetFavoriteComicsFromServerSuccess() {
        adapter.notifyDataSetChanged()
    }

    private fun handleGetFavoriteComicsError(throwable: Throwable) {
        context.showAlert(R.string.error, (throwable as ApiException).errorMessage)
    }

    private fun handleItemClicked(position: Int) {
        //Todo: Open Detail Screen
    }
}
