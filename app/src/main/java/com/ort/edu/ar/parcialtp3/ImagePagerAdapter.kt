package com.ort.edu.ar.parcialtp3
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
class ImagePagerAdapter (private val imageList: List<Int>) : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(imageResId: Int) {
            itemView.findViewById<ImageView>(R.id.imageView).setImageResource(imageResId)
        }
    }
}