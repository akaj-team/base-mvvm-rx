package com.android.asiantech.rx_mvvm_base.ui.main.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.asiantech.rx_mvvm_base.R
import com.android.asiantech.rx_mvvm_base.data.model.Comic
import com.bumptech.glide.Glide

/**
 * @author at-huongnguyen.
 */
class HomeAdapter(private val comics: MutableList<Comic>) : RecyclerView.Adapter<HomeAdapter.HomeItemViewHolder>() {

    override fun getItemCount() = comics.size

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        holder.onBind()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HomeItemViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.home_item, viewGroup, false)
        return HomeItemViewHolder(view)
    }

    internal var onItemClicked: (position: Int) -> Unit = {}

    /**
     * Item view holder for home recycler view.
     */
    inner class HomeItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClicked.invoke(adapterPosition)
            }
        }

        private var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private var tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private var imgHome: ImageView = itemView.findViewById(R.id.imgHome)

        internal fun onBind() {
            val comic = comics[adapterPosition]
            Glide.with(itemView.context)
                    .load(comic.image)
                    .into(imgHome)
            tvTitle.text = comic.name
            tvDescription.text = comic.description
        }
    }
}
