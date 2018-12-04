package com.android.asiantech.rx_mvvm_base.ui.profile

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.model.Manga
import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.extension.observeOnUiThread
import com.android.asiantech.rx_mvvm_base.extension.showAlert
import com.android.asiantech.rx_mvvm_base.ui.base.BaseActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_profile.*

/**
 * @author ChauHQ
 */
class ProfileActivity : BaseActivity() {

    private lateinit var viewModel: ProfileVMContract
    private lateinit var adapter: MangaListAdapter

    companion object {
        private const val COlUMN_NUMBERS = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        viewModel = ProfileViewModel(Repository())
        initRecyclerView()
        initListener()
    }

    override fun onBindViewModel() {
        addDisposables(
                viewModel.updateStateProgressBarObservable()
                        .observeOnUiThread()
                        .subscribe(this::handleStateProgressBar),
                viewModel.getProfile()
                        .observeOnUiThread()
                        .subscribe(this::handleGetUserProfileSuccess, this::handleGetUserProfileError),
                viewModel.getFavoriteMangaList()
                        .observeOnUiThread()
                        .subscribe({ handleGetFavoriteMangasSuccess() }, this::handleGetUserProfileError))
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
        recyclerView.let {
            it.layoutManager = gridLayoutManager
            it.addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.profile_space_between_mange)))
            it.adapter = adapter
            it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount = gridLayoutManager.childCount
                    val totalItemCount = gridLayoutManager.itemCount
                    val firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition()
                    viewModel.loadMore(visibleItemCount, totalItemCount, firstVisibleItem)
                            ?.observeOnUiThread()
                            ?.doAfterSuccess { response -> adapter.notifyDataSetChanged() }
                            ?.subscribe()
                }
            })
        }
    }

    private fun handleGetUserProfileSuccess(user: User) {
        val avatarSize = resources.getDimensionPixelSize(R.dimen.profile_avatar_size)
        tvName.text = user.userName
        Glide.with(this).load(user.avatar)
                .apply(RequestOptions().apply {
                    override(avatarSize, avatarSize)
                    centerCrop()
                }).into(imgAvatar)
    }

    private fun handleGetUserProfileError(throwable: Throwable) {
        showAlert(message = throwable.message)
    }

    private fun eventThumbnailMangaClicked(manga: Manga) {
        // TODO: Move to detail
    }

    private fun eventStarClicked(position: Int) {
        viewModel.updateFavorite(position)
                .observeOnUiThread()
                .doAfterSuccess { handleStarClickSuccess(position) }
                .subscribe()
    }

    private fun handleStateProgressBar(state: Int) {
        progressBar.visibility = state
    }

    private fun handleGetFavoriteMangasSuccess() {
        adapter.notifyDataSetChanged()
    }

    private fun handleStarClickSuccess(position: Int) {
        adapter.notifyItemChanged(position)
    }
}
