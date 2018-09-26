package com.android.asiantech.rx_mvvm_base.extension

import android.content.Context
import android.support.v7.app.AlertDialog
import android.util.DisplayMetrics
import android.view.WindowManager
import com.android.asiantech.rx_mvvm_base.R


/**
 *
 * @author at-vinhhuynh
 */


internal fun Context.getWidthScreen(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as? WindowManager
    val dimension = DisplayMetrics()
    wm?.defaultDisplay?.getMetrics(dimension)
    return dimension.widthPixels
}

internal fun Context.showAlert(title: Int, message: String, onOkClick: () -> Unit = {}) {
    val alertBuilder = AlertDialog.Builder(this)
    alertBuilder.setTitle(title)
    alertBuilder.setMessage(message)
    alertBuilder.setCancelable(true)

    alertBuilder.setPositiveButton(
            R.string.ok,
            { dialog, _ ->
                run {
                    dialog.cancel()
                    onOkClick.invoke()
                }
            })

    val alert = alertBuilder.create()
    alert.show()
}
