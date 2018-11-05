package com.android.asiantech.rx_mvvm_base.ui.profile

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.model.Manga
import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.extension.observeOnUiThread
import com.android.asiantech.rx_mvvm_base.ui.base.BaseActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_profile.*

/**
 * @author ChauHQ
 */
class ProfileActivity : BaseActivity() {
    private lateinit var viewModel: ProfileContract
    private lateinit var adapter: MangaListAdapter
    private var compositeDisposable = CompositeDisposable()

    companion object {
        private const val COlUMN_NUMBERS = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        viewModel = ProfileViewModel(Repository())
        initRecyclerView()
        initObservable()
        initListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun initListener() {
        imgAvatar.setOnClickListener {
            //TODO: Handle edit avatar
        }
    }

    private fun initRecyclerView() {
        adapter = MangaListAdapter(viewModel.getMangaList(), this)
        adapter.onThumbnailClick = this::eventThumbnailMangaClicked
        adapter.onStarClick = this::eventStarClicked
        val gridLayoutManager = GridLayoutManager(this, COlUMN_NUMBERS)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.profile_space_between_mange)))
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = gridLayoutManager.childCount
                val totalItemCount = gridLayoutManager.itemCount
                val firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition()
                viewModel.loadMore(visibleItemCount, totalItemCount, firstVisibleItem)
                        ?.observeOnUiThread()
                        ?.doAfterSuccess { adapter.notifyDataSetChanged() }
                        ?.subscribe()
            }
        })
    }

    private fun initObservable() {
        compositeDisposable.add(
                viewModel.getProfile()
                        .observeOnUiThread()
                        .doOnSubscribe {
                            progressBar.visibility = View.VISIBLE
                        }
                        .doFinally {
                            progressBar.visibility = View.GONE
                        }
                        .subscribe(this::applyDataForProfile, this::showErrorDialog))

        compositeDisposable.add(
                viewModel.getFavoriteMangaList()
                        .observeOnUiThread()
                        .doAfterSuccess { adapter.notifyDataSetChanged() }
                        .subscribe({}, this::showErrorDialog))
    }

    private fun applyDataForProfile(user: User) {
        val avatarSize = resources.getDimensionPixelSize(R.dimen.profile_avatar_size)
        tvName.text = user.userName
        Glide.with(this).load(user.avatar)
                .apply(RequestOptions().apply {
                    override(avatarSize, avatarSize)
                    centerCrop()
                }).into(imgAvatar)
    }

    private fun showErrorDialog(throwable: Throwable) {
        AlertDialog.Builder(this)
                .setMessage(throwable.message)
                .setPositiveButton(android.R.string.ok, null)
                .show()
    }

    private fun eventThumbnailMangaClicked(manga: Manga) {
        // TODO: Move to detail
    }

    private fun eventStarClicked(manga: Manga) {
        viewModel.updateFavorite(manga).observeOnUiThread().doAfterSuccess { adapter.notifyDataSetChanged() }.subscribe()
    }
}

