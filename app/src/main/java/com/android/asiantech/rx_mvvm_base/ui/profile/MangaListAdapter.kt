package com.android.asiantech.rx_mvvm_base.ui.profile

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.model.Manga
import com.android.asiantech.rx_mvvm_base.extension.getScreenWidth
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * @author ChauHQ
 */
class MangaListAdapter(private val mangas: MutableList<Manga>, private val context: Context) : RecyclerView.Adapter<MangaListAdapter.ItemMangaViewHolder>() {
    private var mangaThumbnailHeight = 0
    private var mangaThumbnailWidth = 0
    internal var onStarClick: (manga: Manga) -> Unit = {}
    internal var onThumbnailClick: (manga: Manga) -> Unit = {}

    init {
        mangaThumbnailHeight = context.resources.getDimensionPixelSize(R.dimen.profile_thumbnai_manga_height)
        mangaThumbnailWidth = context.getScreenWidth() / 2
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemMangaViewHolder {
        return ItemMangaViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_manga_downloaded, parent, false))
    }

    override fun getItemCount(): Int = mangas.size

    override fun onBindViewHolder(holder: ItemMangaViewHolder?, position: Int) {
        holder?.bind(mangas[position])
    }

    inner class ItemMangaViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView?.findViewById<TextView>(R.id.tvName)
        private val tvDescription = itemView?.findViewById<TextView>(R.id.tvDescription)
        private val imgAvatar = itemView?.findViewById<ImageView>(R.id.imgAvatar)
        private val imgStar = itemView?.findViewById<ImageView>(R.id.imgStar)

        init {
            imgAvatar?.setOnClickListener {
                onThumbnailClick.invoke(mangas[layoutPosition])
            }

            imgStar?.setOnClickListener {
                onStarClick.invoke(mangas[layoutPosition])
            }
        }

        fun bind(manga: Manga) {
            tvName?.text = manga.name
            tvDescription?.text = manga.description
            imgAvatar?.let {
                Glide.with(context).load(manga.thumbnail)
                        .apply(RequestOptions().apply {
                            override(mangaThumbnailWidth, mangaThumbnailHeight)
                            centerCrop()
                        }).into(it)
            }
        }
    }
}
