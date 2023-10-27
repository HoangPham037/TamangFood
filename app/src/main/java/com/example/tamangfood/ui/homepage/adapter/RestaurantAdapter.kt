package com.example.tamangfood.ui.homepage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemFeaturedPartnersHorizontalBinding
import com.example.tamangfood.extensions.convertToStringOneNumber
import com.example.tamangfood.extensions.loadImg
import com.example.tamangfood.ui.featuredpartners.OnItemClickListener
import com.example.tamangfood.ui.homepage.model.Partners

class RestaurantAdapter(
    private val myList: List<Partners>,
    private val context: Context,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemFeaturedPartnersHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(partners: Partners) = with(binding) {
            context.loadImg(partners.imgUrl, imgItem)
            tvTitle.text = partners.name
            tvDescription.text = partners.description
            tvRating.text = partners.rating?.convertToStringOneNumber()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFeaturedPartnersHorizontalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = myList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myList[position])
        holder.itemView.setOnClickListener {
            listener.onItemClick(myList[position])
        }
    }
}