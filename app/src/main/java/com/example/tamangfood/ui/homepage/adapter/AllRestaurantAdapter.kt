package com.example.tamangfood.ui.homepage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.tamangfood.common.Config.setCurrentIndicator
import com.example.tamangfood.common.Config.setIndicator
import com.example.tamangfood.databinding.ItemRestaurantBinding
import com.example.tamangfood.ui.homepage.model.Partners

class AllRestaurantAdapter(private val myList: List<Partners>, val context: Context) :
    RecyclerView.Adapter<AllRestaurantAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var pagerAdapter: PagerAdapter
        fun bind(partners: Partners) = with(binding) {
            pagerAdapter = PagerAdapter(partners.slider, context)
            viewPagerRestaurant.adapter = pagerAdapter
            setIndicator(indicatorLayout, pagerAdapter.itemCount, context)
            setCurrentIndicator(0, indicatorLayout, context)

            viewPagerRestaurant.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentIndicator(position, indicatorLayout, context)
                }
            })
            tvName.text = partners.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRestaurantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = myList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(myList[position])
}