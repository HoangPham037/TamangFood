package com.example.tamangfood.ui.homepage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemFeaturedPartnersHorizontalBinding
import com.example.tamangfood.ui.homepage.model.Partners

class RestaurantAdapter(private val myList: List<Partners>) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemFeaturedPartnersHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(partners: Partners) = with(binding) {
            imgItem.setImageResource(partners.img)
            tvTitle.text = partners.name
            tvDescription.text = partners.description
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(myList[position])
}