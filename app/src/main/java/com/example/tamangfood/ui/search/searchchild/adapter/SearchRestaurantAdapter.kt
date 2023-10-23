package com.example.tamangfood.ui.search.searchchild.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemRecentSearchesBinding
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.ui.featuredpartners.OnItemClickListener
import com.example.tamangfood.ui.homepage.model.Partners

class SearchRestaurantAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<SearchRestaurantAdapter.ViewHolder>(){
    private var myList= listOf<Partners>()
    inner class ViewHolder(private val binding: ItemRecentSearchesBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(partners: Partners) = with(binding) {
            tvItemSearch.text = partners.name
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(ItemRecentSearchesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myList[position])
        holder.itemView.setSafeOnClickListener {
            listener.onItemClick(myList[position])
        }
    }

    fun updateList(newList:List<Partners>) {
        this.myList = newList
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return myList.size
    }
}