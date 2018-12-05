package com.android.asiantech.rx_mvvm_base.ui.main.favorite

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_favorite.view.*

/**
 * Copyright © 2018 AsianTech inc.
 * Created by TaiND on 10/31/18.
 */
class FavoriteAdapter(private val listComics: List<Comic>) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    internal var onItemClicked: (position: Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false))
    }

    override fun getItemCount(): Int = listComics.size


    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindView(position)
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { onItemClicked(adapterPosition) }
        }

        fun bindView(position: Int) {
            with(listComics[position]) {
                with(itemView) {
                    Glide.with(itemView.context).load(image).into(imgComic)
                    tvComicName.text = name
                    tvComicDescription.text = description
                    imgFavorite.isSelected = likeFlag
                }
            }
        }
    }
}
