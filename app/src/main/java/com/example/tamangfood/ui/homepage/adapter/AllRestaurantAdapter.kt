package com.example.tamangfood.ui.homepage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.tamangfood.R
import com.example.tamangfood.databinding.ItemRestaurantBinding
import com.example.tamangfood.ui.homepage.model.Partners

class AllRestaurantAdapter(private val myList: List<Partners>, val context: Context) :
    RecyclerView.Adapter<AllRestaurantAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var pagerAdapter: PagerAdapter
        fun bind(partners: Partners) = with(binding) {
            pagerAdapter = PagerAdapter(partners.listPage)
            viewPagerRestaurant.adapter = pagerAdapter
            setIndicator(indicatorLayout, pagerAdapter.itemCount)
            setCurrentIndicator(0, indicatorLayout)

            viewPagerRestaurant.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentIndicator(position, indicatorLayout)
                }
            })
            tvName.text = partners.name
            tvCurrency.text = partners.listType!![0]
            tvTypeOne.text = partners.listType[1]
            tvTypeTwo.text = partners.listType[2]
            tvTypeFood.text = partners.listType[3]
        }

        private fun setIndicator(view: ViewGroup, pageSize: Int) {
            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(8, 0, 8, 0)

            for (i in 0 until pageSize) {
                val imageView = ImageView(context)
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        context, R.drawable.indicator_inactive
                    )
                )
                imageView.layoutParams = layoutParams
                view.addView(imageView)
            }
        }

        private fun setCurrentIndicator(index: Int, view: ViewGroup) {
            val childCount = view.childCount
            for (i in 0 until childCount) {
                val image = view.getChildAt(i) as ImageView
                if (i == index) {
                    image.setImageDrawable(
                        ContextCompat.getDrawable(
                            context, R.drawable.indicator_active
                        )
                    )
                } else {
                    image.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.indicator_inactive
                        )
                    )
                }
            }

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