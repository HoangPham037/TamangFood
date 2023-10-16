package com.example.tamangfood.ui.featuredpartners

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentFeaturedPartnersBinding
import com.example.tamangfood.ui.featuredpartners.adapter.RestaurantVerticalAdapter
import com.example.tamangfood.ui.homepage.HomeViewModel

class FeaturedPartnersFragment : BaseFragment<FragmentFeaturedPartnersBinding>(
    FragmentFeaturedPartnersBinding::inflate
) {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapter: RestaurantVerticalAdapter

    override fun observerData() {
        super.observerData()
        homeViewModel.dataFeaturedPartners.observe(viewLifecycleOwner) {
            adapter = RestaurantVerticalAdapter(it)
            binding.rcFeaturedPartners.adapter = adapter
        }
        homeViewModel.fetchListFeaturedPartners()
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.icBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}