package com.example.tamangfood.ui.search.searchchild.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.LayoutSearchRestaurantBinding
import com.example.tamangfood.extensions.loadImg
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.ui.featuredpartners.OnClickRestaurant
import com.example.tamangfood.ui.homepage.model.Partners

@SuppressLint("NotifyDataSetChanged")
class SearchRestaurantAdapter(
    private val listener: OnClickRestaurant,
    private val context: Context
) : RecyclerView.Adapter<SearchRestaurantAdapter.ViewHolder>() {
    private var myList = ArrayList<Partners>()

    inner class ViewHolder(private val binding: LayoutSearchRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(partners: Partners) = with(binding) {
            partners.also { restaurant ->
                tvNameRestaurant.text = restaurant.name
                tvDescription.text = restaurant.description
                context.loadImg(restaurant.imgUrl, imgRestaurant)
                tvPointNumber.text = restaurant.rating.toString()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutSearchRestaurantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myList[position])
        holder.itemView.setSafeOnClickListener {
            listener.onItemClick(myList[position])
        }
    }

    fun updateList(newList: ArrayList<Partners>) {
        this.myList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}