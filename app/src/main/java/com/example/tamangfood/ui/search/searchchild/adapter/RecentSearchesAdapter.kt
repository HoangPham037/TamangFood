package com.example.tamangfood.ui.search.searchchild.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemRecentSearchesBinding
import com.example.tamangfood.ui.search.searchchild.local.model.RecentSearchesData

class RecentSearchesAdapter(): RecyclerView.Adapter<RecentSearchesAdapter.ViewHolder>() {
    private var yourDataList = listOf<RecentSearchesData>()
    inner class ViewHolder(private val binding: ItemRecentSearchesBinding ): RecyclerView.ViewHolder(binding.root){
        fun bind(partners: RecentSearchesData) = with(binding) {
            tvItemSearch.text = partners.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecentSearchesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int =yourDataList.size

    fun updateData(newData: List<RecentSearchesData>) {
        yourDataList = newData
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(yourDataList[position])
    }
}