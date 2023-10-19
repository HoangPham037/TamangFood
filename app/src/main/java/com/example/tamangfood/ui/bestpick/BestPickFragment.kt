package com.example.tamangfood.ui.bestpick

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentBestPickBinding
import com.example.tamangfood.ui.featuredpartners.OnItemClickListener
import com.example.tamangfood.ui.featuredpartners.adapter.RestaurantVerticalAdapter
import com.example.tamangfood.ui.homepage.HomeViewModel
import com.example.tamangfood.ui.homepage.model.Partners
import com.example.tamangfood.ui.singlerestaurent.model.Product

class BestPickFragment : BaseFragment<FragmentBestPickBinding>(
    FragmentBestPickBinding::inflate
), OnItemClickListener {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapter: RestaurantVerticalAdapter
    override fun observerData() {
        super.observerData()
        homeViewModel.dataBestPickRestaurant.observe(viewLifecycleOwner) {
            adapter = RestaurantVerticalAdapter(it, this)
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

    override fun onItemClick(partners: Partners) {

    }

}