package com.example.tamangfood.ui.singlerestaurent.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tamangfood.ui.singlerestaurent.typefood.AppetizersFragment
import com.example.tamangfood.ui.singlerestaurent.typefood.SeeFoodFragment

class TypeFoodVPAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SeeFoodFragment()
            1 -> AppetizersFragment()
            else -> SeeFoodFragment()
        }
    }
}