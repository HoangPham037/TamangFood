package com.example.tamangfood.ui.search.filters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemFiltersCategoryBinding

class FiltersAdapter(private val myList: List<CategoriesData>): RecyclerView.Adapter<FiltersAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemFiltersCategoryBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(categoriesData: CategoriesData) = with(binding) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiltersAdapter.ViewHolder {
        return ViewHolder(ItemFiltersCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FiltersAdapter.ViewHolder, position: Int) {
        holder.bind(myList[position])
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}