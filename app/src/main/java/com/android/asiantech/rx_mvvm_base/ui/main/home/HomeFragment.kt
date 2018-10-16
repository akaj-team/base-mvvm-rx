package com.android.asiantech.rx_mvvm_base.ui.main.home

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiException
import com.android.asiantech.rx_mvvm_base.extension.observeOnUiThread
import com.android.asiantech.rx_mvvm_base.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 10/4/18.
 */
class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeVMContract
    private lateinit var adapter: HomeAdapter
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = HomeViewModel(Repository())
        adapter = HomeAdapter(viewModel.getComics())
        adapter.onItemClicked = this::handleItemClicked
        adapter.onItemDoubleClicked = this::handleItemDoubleClicked
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initProgressDialog()
    }

    override fun onBindViewModel() {
        addDisposables(viewModel.updateProgressDialogStatus().subscribe(this::handleDisplayDialog),
                viewModel.getComicsFromServer()
                        .observeOnUiThread()
                        .subscribe({ adapter.notifyDataSetChanged() }, this::handleLoadComicsError))
    }

    private fun initRecyclerView() {
        val gridLayoutManager = GridLayoutManager(activity, 2)
        recyclerViewHome.layoutManager = gridLayoutManager
        recyclerViewHome.adapter = adapter

        recyclerViewHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(context)
        progressDialog.setCancelable(false)
    }

    private fun handleItemClicked(position: Int) {
        //Todo: Open Detail Screen
    }

    private fun handleItemDoubleClicked(position: Int) {
        if (viewModel.isFavorite(position)) {
            viewModel.unFavorite(position)
                    .observeOnUiThread()
                    .subscribe({
                        handleUnFavoriteSuccess(it.success, position)
                    }, this::handleFavoriteError)
        } else {
            viewModel.favorite(position)
                    .observeOnUiThread()
                    .subscribe({
                        handleFavoriteSuccess(it.success, position)
                    }, this::handleFavoriteError)
        }
    }

    private fun handleDisplayDialog(status: Boolean) {
        activity.runOnUiThread {
            if (status) {
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        }
    }

    private fun handleLoadComicsError(error: Throwable) {
        Toast.makeText(activity, (error as? ApiException)?.errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun handleFavoriteSuccess(success: Boolean, position: Int) {
        if (success) {
            //Todo: Handle later
        }
    }

    private fun handleUnFavoriteSuccess(success: Boolean, position: Int) {
        if (success) {
            //Todo: Handle later
        }
    }

    private fun handleFavoriteError(error: Throwable) {
        Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
    }
}
