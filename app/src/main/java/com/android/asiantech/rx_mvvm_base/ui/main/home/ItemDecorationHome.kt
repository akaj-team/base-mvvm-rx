package com.android.asiantech.rx_mvvm_base.ui.main.home

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout

/**
 * @author at-huongnguyen.
 */
class ItemDecorationHome(private val sizeGridSpacingPx: Int, private val gridColumn: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val itemPosition = (view.layoutParams as? RecyclerView.LayoutParams)?.viewAdapterPosition
        itemPosition?.let {
            if (it < gridColumn) {
                outRect.top = 0
            } else {
                outRect.top = sizeGridSpacingPx
            }
            if (it % gridColumn == 0) {
                outRect.right = sizeGridSpacingPx / 2
                outRect.left = 0
            } else {
                outRect.left = sizeGridSpacingPx / 2
                outRect.right = 0
            }
        }
    }
}
