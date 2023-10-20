package com.example.tamangfood.ui.homepage

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentHomeBinding
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.ui.featuredpartners.OnItemClickListener
import com.example.tamangfood.ui.homepage.adapter.AllRestaurantAdapter
import com.example.tamangfood.ui.homepage.adapter.PagerAdapter
import com.example.tamangfood.ui.homepage.adapter.RestaurantAdapter
import com.example.tamangfood.ui.homepage.model.Partners

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
), OnItemClickListener {

    private val homeViewModel: HomeViewModel by viewModels()
    private val shareViewModel: ShareViewModel by activityViewModels()
    private lateinit var restaurantFeaturedAdapter: RestaurantAdapter
    private lateinit var restaurantBestPickAdapter: RestaurantAdapter
    private lateinit var allRestaurantAdapter: AllRestaurantAdapter
    private lateinit var pagerAdapter: PagerAdapter

    override fun observerData() {
        super.observerData()
        observerDataPager()
        observerDataFeaturedPartners()
        observerDataBestPickRestaurant()
        observerDataAllRestaurant()
    }

    private fun observerDataPager() {
        homeViewModel.dataPager.observe(viewLifecycleOwner) {
            pagerAdapter = PagerAdapter(it)
            binding.viewPager.adapter = pagerAdapter
            binding.indicator.setViewPager(binding.viewPager)
            binding.viewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
//                    handler.removeCallbacks(runnable)
//                    handler.postDelayed(runnable, 3000)
                }
            })
        }
        homeViewModel.fetchDataPager()
    }

    private fun observerDataFeaturedPartners() {
        homeViewModel.dataFeaturedPartners.observe(viewLifecycleOwner) { listFeaturedPartners ->
            restaurantFeaturedAdapter = RestaurantAdapter(listFeaturedPartners, this)
            binding.rcFeaturedPartners.adapter = restaurantFeaturedAdapter
        }
        homeViewModel.fetchListFeaturedPartners()
    }

    private fun observerDataBestPickRestaurant() {
        homeViewModel.dataBestPickRestaurant.observe(viewLifecycleOwner) { listFeaturedPartners ->
            restaurantBestPickAdapter = RestaurantAdapter(listFeaturedPartners, this)
            binding.rcBestPick.adapter = restaurantBestPickAdapter
        }
        homeViewModel.fetchListBestPickRestaurant()
    }

    private fun observerDataAllRestaurant() {
        homeViewModel.dataAllRestaurant.observe(viewLifecycleOwner) { listAll ->
            allRestaurantAdapter = AllRestaurantAdapter(listAll, requireContext())
            binding.rcAllRestaurant.adapter = allRestaurantAdapter
        }
        homeViewModel.fetchListAllRestaurant()
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.seeAllFeaturedPartners.setSafeOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_featuredPartnersFragment)
        }
        binding.seeAllBestPick.setSafeOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bestPickFragment)
        }
    }

    override fun onItemClick(partners: Partners) {
        shareViewModel.selectRestaurant.value = partners
        findNavController().navigate(R.id.action_homeFragment_to_singleRestaurantFragment)
    }

}