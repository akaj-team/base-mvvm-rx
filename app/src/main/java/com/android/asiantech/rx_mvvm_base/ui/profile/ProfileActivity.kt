package com.android.asiantech.rx_mvvm_base.ui.profile

import android.os.Bundle
import android.os.PersistableBundle
import android.os.UserHandle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.model.User
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.extension.observeOnUiThread
import com.android.asiantech.rx_mvvm_base.ui.base.BaseActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    private fun initRecyclerView() {
        adapter = MangaListAdapter(viewModel.getMangaList(), this)
        recyclerView.layoutManager = GridLayoutManager(this, COlUMN_NUMBERS)
        recyclerView.addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.profile_space_between_mange)))
        recyclerView.adapter = adapter
    }

    private fun initObservable() {
        compositeDisposable.add(
                viewModel.getProfile()
                        .observeOnUiThread()
                        .subscribe(this::applyDataForProfile, this::showErrorDialog))

        compositeDisposable.add(
                viewModel.getFavoriteMangaList()
                        .observeOnUiThread()
                        .subscribe({ adapter.notifyDataSetChanged() }, this::showErrorDialog))
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
}

