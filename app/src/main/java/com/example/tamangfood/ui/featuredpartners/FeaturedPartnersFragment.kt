package com.example.tamangfood.ui.featuredpartners

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentFeaturedPartnersBinding
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.ui.featuredpartners.adapter.RestaurantVerticalAdapter
import com.example.tamangfood.ui.homepage.HomeViewModel
import com.example.tamangfood.ui.homepage.model.Partners

@Suppress("UNREACHABLE_CODE")
class FeaturedPartnersFragment : BaseFragment<FragmentFeaturedPartnersBinding>(
    FragmentFeaturedPartnersBinding::inflate
), OnItemClickListener {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapter: RestaurantVerticalAdapter
    private val shareViewModel: ShareViewModel by activityViewModels()

    override fun observerData() {
//        super.observerData()
//        homeViewModel.dataFeaturedPartners.observe(viewLifecycleOwner) {
//            adapter = RestaurantVerticalAdapter(it, this,requireContext())
//            binding.rcFeaturedPartners.adapter = adapter
//        }
//        homeViewModel.fetchListFeaturedPartners()
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.icBack.setSafeOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onItemClick(partners: Partners) {
        shareViewModel.selectRestaurant.value = partners
        findNavController().navigate(R.id.action_featuredPartnersFragment_to_singleRestaurantFragment)
    }
}