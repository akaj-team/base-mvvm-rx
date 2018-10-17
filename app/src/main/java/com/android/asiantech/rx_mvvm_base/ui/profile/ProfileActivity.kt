package com.android.asiantech.rx_mvvm_base.ui.profile

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.GridLayoutManager
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_profile.*

/**
 * @author ChauHQ
 */
class ProfileActivity : BaseActivity() {

    companion object {
        private const val COlUMN_NUMBERS = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initRecyclerView()
    }


    private fun initRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(this, COlUMN_NUMBERS)
        recyclerView.addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.profile_space_between_mange)))
        recyclerView.adapter = MangaListAdapter()
    }
}



