package com.example.tamangfood.ui.featuredpartners.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemFeatruredPartnersVerticalBinding
import com.example.tamangfood.ui.homepage.model.Partners

class RestaurantVerticalAdapter(private val myList: List<Partners>) :
    RecyclerView.Adapter<RestaurantVerticalAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemFeatruredPartnersVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(partners: Partners) = with(binding) {
            imgItem.setImageResource(partners.img)
            tvNamePartner.text = partners.name
            tvTypeOne.text = partners.listType!![1]
            tvTypeTwo.text = partners.listType[2]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFeatruredPartnersVerticalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int =myList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(myList[position])
}