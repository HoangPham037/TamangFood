package com.example.tamangfood.ui.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.R
import com.example.tamangfood.databinding.ItemBoardingBinding

class PagerAdapter(private val items:ArrayList<OnboardingData>): RecyclerView.Adapter<PagerAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemBoardingBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(onboardingData: OnboardingData) = with(binding) {
//            when (onboardingData.img) {
//                R.drawable.illustrations_three -> {
//                    val params = binding.imgItem.layoutParams as ViewGroup.MarginLayoutParams
//                    params.setMargins(0,0,0,0 )
//                    binding.imgItem.layoutParams = params
//                }
//            }

            binding.tvTitle.text = onboardingData.title
            binding.tvDescription.text = onboardingData.description
            binding.imgItem.setImageResource(onboardingData.img)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerAdapter.ViewHolder {
        return ViewHolder(ItemBoardingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: PagerAdapter.ViewHolder, position: Int) =holder.bind(items[position])

    override fun getItemCount(): Int = items.size
}