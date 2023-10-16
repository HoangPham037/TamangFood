package com.example.tamangfood.ui.singlerestaurent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemFeaturedItemsBinding
import com.example.tamangfood.ui.singlerestaurent.model.Product

class FeaturedItemAdapter(private val myList: List<Product>) :
    RecyclerView.Adapter<FeaturedItemAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemFeaturedItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) = with(binding) {
            imgFeaturedItem.setImageResource(product.img)
            tvNameItem.text = product.name
            tvCurrency.text = product.listType[0]
            tvNameType.text = product.listType[1]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFeaturedItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = myList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(myList[position])
}