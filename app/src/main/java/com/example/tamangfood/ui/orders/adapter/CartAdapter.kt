package com.example.tamangfood.ui.orders.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemOrdersBinding
import com.example.tamangfood.ui.orders.model.OrderData

class CartAdapter(private val myList: List<OrderData>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemOrdersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(orderData: OrderData) = with(binding) {
            tvNameProduct.text = orderData.product?.name
            tvDesProduct.text = orderData.product?.description
            tvTotal.text = String.format("%s%d", "aud$", orderData.quality)
            tvQuality.text = orderData.quality.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemOrdersBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int =myList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myList[position])
        holder.itemView.setOnClickListener {

        }
    }
}