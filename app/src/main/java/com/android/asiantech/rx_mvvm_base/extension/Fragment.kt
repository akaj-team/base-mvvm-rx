package com.uniqlo.circle.extension

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.android.asiantech.rx_mvvm_base.R

/**
 *
 * @author at-vinhhuynh
 */
internal fun Fragment.getCurrentFragment(@IdRes containerId: Int) = childFragmentManager.findFragmentById(containerId)

internal fun FragmentTransaction.animSlideInRightSlideOutRight() {
    setCustomAnimations(R.anim.slide_in_right, 0, 0, R.anim.slide_out_right)
}
