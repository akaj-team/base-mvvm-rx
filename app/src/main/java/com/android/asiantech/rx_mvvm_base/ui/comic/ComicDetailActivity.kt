package com.android.asiantech.rx_mvvm_base.ui.comic

import android.os.Bundle
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.extension.replaceFragment
import com.android.asiantech.rx_mvvm_base.ui.base.BaseActivity
import com.uniqlo.circle.extension.animSlideInRightSlideOutRight

class ComicDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        replaceFragment(R.id.userActivityContainer, ComicDetailFragment(), {
            it.animSlideInRightSlideOutRight()
        })
    }
}
