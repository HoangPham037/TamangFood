package com.example.tamangfood.ui.homepage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemPagerBinding
import com.example.tamangfood.ui.homepage.model.ItemPager

class PagerAdapter(private val myList: List<ItemPager>?) :
    RecyclerView.Adapter<PagerAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemPager: ItemPager) = with(binding) {
            imgPager.setImageResource(itemPager.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = myList!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(myList!![position])
}