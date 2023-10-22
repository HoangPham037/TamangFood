package com.example.tamangfood.ui.singlerestaurent.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemProductBinding
import com.example.tamangfood.extensions.loadImg
import com.example.tamangfood.ui.singlerestaurent.IOnItemClickListener
import com.example.tamangfood.ui.singlerestaurent.model.Product


class TypeFoodAdapter(private val myList: List<Product>,private val context: Context, private val listener: IOnItemClickListener) :
    RecyclerView.Adapter<TypeFoodAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) = with(binding) {
            context.loadImg(product.imgUrl, imgItemProduct)
            tvNameProduct.text = product.name
            tvDescriptionProduct.text = product.description
            price.text = String.format("%s%.1f", "aud$", product.price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
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