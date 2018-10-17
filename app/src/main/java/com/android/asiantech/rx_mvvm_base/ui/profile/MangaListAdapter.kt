package com.android.asiantech.rx_mvvm_base.ui.profile

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.asiantech.rx_mvvm_base.R

/**
 * @author ChauHQ
 */
class MangaListAdapter : RecyclerView.Adapter<MangaListAdapter.ItemMangaViewHoder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemMangaViewHoder {
        return ItemMangaViewHoder(LayoutInflater.from(parent?.context).inflate(R.layout.item_manga_downloaded, parent, false))
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: ItemMangaViewHoder?, position: Int) {
    }

    class ItemMangaViewHoder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}