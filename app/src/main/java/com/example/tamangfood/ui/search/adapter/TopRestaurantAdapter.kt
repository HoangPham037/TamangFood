package com.example.tamangfood.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemFeaturedItemsBinding
import com.example.tamangfood.extensions.loadImg
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.ui.featuredpartners.OnItemClickListener
import com.example.tamangfood.ui.homepage.model.Partners

class TopRestaurantAdapter(private val myList: List<Partners>, private val listener: OnItemClickListener, private val context: Context) : RecyclerView.Adapter<TopRestaurantAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemFeaturedItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(partners: Partners) = with(binding) {
            context.loadImg(partners.imgUrl, imgFeaturedItem)
            tvNameItem.text  = partners.name

            val width = context.resources.displayMetrics.widthPixels
            imgFeaturedItem.layoutParams.width = (width / 2) - 85
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFeaturedItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = myList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myList[position])
        holder.itemView.setSafeOnClickListener {
            listener.onItemClick(myList[position])
        }
    }
}