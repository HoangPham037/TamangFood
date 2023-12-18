package com.example.tamangfood.ui.orders.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemProductBinding
import com.example.tamangfood.extensions.convertToStringWithAUD
import com.example.tamangfood.extensions.loadImg
import com.example.tamangfood.ui.singlerestaurent.addtoorders.RestaurantOrder
@SuppressLint("NotifyDataSetChanged")
class OrderAdapter constructor(private val context: Context): RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    private var myList = ArrayList<RestaurantOrder>()
    inner class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (restaurantOrder: RestaurantOrder) = with(binding) {
            restaurantOrder.also {
                context.loadImg(restaurantOrder.restaurant?.imgUrl, imgItemProduct)
                tvNameProduct.text = it.restaurant?.name
                tvDescriptionProduct.text = it.restaurant?.description
                tvCurrency.text = it.restaurant?.currency
                tvNameType.text = it.restaurant?.country?.get(0)
                price.text = it.listOrder?.map {orderData->
                    orderData.total
                }?.reduce { acc, fl ->  acc!!+ fl!!}?.convertToStringWithAUD()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: OrderAdapter.ViewHolder, position: Int) {
        holder.bind(myList[position])
    }

    fun setList(newList: ArrayList<RestaurantOrder>) {
        this.myList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}