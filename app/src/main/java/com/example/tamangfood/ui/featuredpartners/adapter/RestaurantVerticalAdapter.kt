package com.example.tamangfood.ui.featuredpartners.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemFeatruredPartnersVerticalBinding
import com.example.tamangfood.extensions.loadImg
import com.example.tamangfood.ui.featuredpartners.OnItemClickListener
import com.example.tamangfood.ui.homepage.model.Partners


class RestaurantVerticalAdapter(
    private val myList: List<Partners>,
    private val listener: OnItemClickListener,
    private val context: Context
) :
    RecyclerView.Adapter<RestaurantVerticalAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemFeatruredPartnersVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(partners: Partners) = with(binding) {
            context.loadImg(partners.imgUrl, imgItem)
            tvNamePartner.text = partners.name

            val width = context.resources.displayMetrics.widthPixels
            imgItem.layoutParams.width = (width / 2) - 75
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

    override fun getItemCount(): Int = myList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myList[position])
        holder.itemView.setOnClickListener {
            listener.onItemClick(myList[position])
        }
    }
}