package com.example.tamangfood.ui.singlerestaurent.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemFeaturedItemsBinding
import com.example.tamangfood.extensions.loadImg
import com.example.tamangfood.ui.singlerestaurent.IOnItemClickListener
import com.example.tamangfood.ui.singlerestaurent.model.Product

class FeaturedItemAdapter(
    private val myList: List<Product>,
    private val listener: IOnItemClickListener,
    private val context: Context,
    private val mode : Int
) :
    RecyclerView.Adapter<FeaturedItemAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemFeaturedItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) = with(binding) {
            context.loadImg(product.imgUrl, imgFeaturedItem)
            tvNameItem.text = product.name
            val layoutManager = layoutContainer.layoutParams
            layoutManager.width = mode
            layoutContainer.layoutParams= layoutManager
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myList[position])
        holder.itemView.setOnClickListener {
            listener.itemClick(myList[position])
        }
    }
}