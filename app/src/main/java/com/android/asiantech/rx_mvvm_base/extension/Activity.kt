@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

package com.android.asiantech.rx_mvvm_base.extension

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import com.android.asiantech.rx_mvvm_base.ui.base.BaseFragment

/**
 *
 * @author at-vinhhuynh
 */
internal fun FragmentActivity.replaceFragment(@IdRes containerId: Int, fragment: Fragment,
                                              t: (transaction: FragmentTransaction) -> Unit = {},
                                              isAddBackStack: Boolean = false) {
    if (supportFragmentManager.findFragmentByTag(fragment.javaClass.simpleName) == null) {
        val transaction = supportFragmentManager.beginTransaction()
        t.invoke(transaction)
        transaction.replace(containerId, fragment, fragment.javaClass.simpleName)
        if (isAddBackStack) {
            transaction.addToBackStack(fragment.javaClass.simpleName)
        }
        transaction.commit()
    }
}

internal fun FragmentActivity.addFragment(@IdRes containerId: Int, fragment: BaseFragment,
                                          t: (transaction: FragmentTransaction) -> Unit = {}, backStackString: String? = null,
                                          tag: String) {
    if (supportFragmentManager.findFragmentByTag(tag) == null) {
        val transaction = supportFragmentManager.beginTransaction()
        t.invoke(transaction)
        transaction.add(containerId, fragment, tag)
        if (backStackString != null) {
            transaction.addToBackStack(backStackString)
        }
        transaction.commit()
        supportFragmentManager.executePendingTransactions()
    }
}

internal fun FragmentActivity.getCurrentFragment(@IdRes containerId: Int) = supportFragmentManager.findFragmentById(containerId)

internal fun FragmentActivity.popFragment() {
    supportFragmentManager.popBackStackImmediate()
}
