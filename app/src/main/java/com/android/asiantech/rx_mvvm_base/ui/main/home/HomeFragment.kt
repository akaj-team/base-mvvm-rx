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
 * @author at-haingo
 */
class HomeFragment : BaseFragment() {

    companion object {
        private const val GIRD_COLUMN = 2
    }
    
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
        viewModel.getComicsFromServer()
    }

    override fun onBindViewModel() {
        addDisposables(viewModel.updateProgressDialogStatus()
                .observeOnUiThread()
                .subscribe(this::handleDisplayDialog),
                viewModel.getComicsObservable()
                        .observeOnUiThread()
                        .subscribe({ adapter.notifyDataSetChanged() }, this::handleLoadComicsError))
    }

    private fun initRecyclerView() {
        recyclerViewHome.addItemDecoration(ItemDecorationHome(resources.getDimensionPixelSize(R.dimen.home_fragment_recyclerview_grid_spacing), GIRD_COLUMN))
        val gridLayoutManager = GridLayoutManager(activity, GIRD_COLUMN)
        recyclerViewHome.layoutManager = gridLayoutManager
        recyclerViewHome.adapter = adapter

        recyclerViewHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
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

    @SuppressWarnings("checkResult")
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
        if (status) {
            progressDialog.show()
        } else {
            progressDialog.dismiss()
        }
    }

    private fun handleLoadComicsError(error: Throwable) {
        Toast.makeText(activity, (error as? ApiException)?.errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun handleFavoriteSuccess(success: Boolean, position: Int) {
        if (success) {
            adapter.notifyItemChanged(position)
        }
    }

    private fun handleUnFavoriteSuccess(success: Boolean, position: Int) {
        if (success) {
            adapter.notifyItemChanged(position)
        }
    }

    private fun handleFavoriteError(error: Throwable) {
        Toast.makeText(activity, (error as? ApiException)?.errorMessage, Toast.LENGTH_SHORT).show()
    }
}
