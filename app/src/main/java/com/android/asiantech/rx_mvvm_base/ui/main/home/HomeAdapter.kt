package com.android.asiantech.rx_mvvm_base.ui.main.home

import android.support.v7.widget.RecyclerView
import android.view.*
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
    internal var onItemDoubleClicked: (position: Int) -> Unit = {}

    /**
     * Item view holder for home recycler view.
     */
    inner class HomeItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val gestureDetector = GestureDetector(itemView.context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                onItemClicked.invoke(adapterPosition)
                return true
            }

            override fun onDoubleTap(e: MotionEvent?): Boolean {
                onItemDoubleClicked.invoke(adapterPosition)
                return true
            }
        })

        private var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private var tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private var imgThumb: ImageView = itemView.findViewById(R.id.imgThumb)
        private var imgFavorite: ImageView = itemView.findViewById(R.id.imgFavorite)

        init {
            imgThumb.setOnTouchListener { _, event ->
                gestureDetector.onTouchEvent(event)
                true
            }
        }

        internal fun onBind() {
            val comic = comics[adapterPosition]
            with(comic) {
                Glide.with(itemView.context)
                        .load(image)
                        .into(imgThumb)
                tvTitle.text = name
                tvDescription.text = description
                imgFavorite.isSelected = likeFlag
            }
        }
    }
}
