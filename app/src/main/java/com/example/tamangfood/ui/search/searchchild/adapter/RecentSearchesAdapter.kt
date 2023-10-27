package com.example.tamangfood.ui.search.searchchild.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemRecentSearchesBinding
import com.example.tamangfood.ui.search.searchchild.local.model.RecentSearchesData
@SuppressLint("NotifyDataSetChanged")
class RecentSearchesAdapter(): RecyclerView.Adapter<RecentSearchesAdapter.ViewHolder>() {
    private var myDataList = ArrayList<RecentSearchesData>()
    inner class ViewHolder(private val binding: ItemRecentSearchesBinding ): RecyclerView.ViewHolder(binding.root){
        fun bind(partners: RecentSearchesData) = with(binding) {
            tvItemSearch.text = partners.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecentSearchesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int =myDataList.size

    fun updateData(newData: ArrayList<RecentSearchesData>) {
        myDataList = newData
        notifyDataSetChanged()
    }
    fun clearData() {
        this.myDataList.clear()
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myDataList[position])
    }
}