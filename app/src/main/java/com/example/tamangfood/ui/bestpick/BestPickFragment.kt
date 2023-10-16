package com.example.tamangfood.ui.bestpick

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentBestPickBinding
import com.example.tamangfood.ui.featuredpartners.adapter.RestaurantVerticalAdapter
import com.example.tamangfood.ui.homepage.HomeViewModel

class BestPickFragment : BaseFragment<FragmentBestPickBinding>(
    FragmentBestPickBinding::inflate
) {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapter: RestaurantVerticalAdapter
    override fun observerData() {
        super.observerData()
        homeViewModel.dataBestPickRestaurant.observe(viewLifecycleOwner) {
            adapter = RestaurantVerticalAdapter(it)
            binding.rcBestPick.adapter = adapter
        }
        homeViewModel.fetchListBestPickRestaurant()
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.icBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}