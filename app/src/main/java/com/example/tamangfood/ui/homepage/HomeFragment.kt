package com.example.tamangfood.ui.homepage

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.common.Config
import com.example.tamangfood.databinding.FragmentHomeBinding
import com.example.tamangfood.extensions.getMySharedPreferences
import com.example.tamangfood.extensions.putInt
import com.example.tamangfood.extensions.putString
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.ui.featuredpartners.OnItemClickListener
import com.example.tamangfood.ui.homepage.adapter.AllRestaurantAdapter
import com.example.tamangfood.ui.homepage.adapter.PagerAdapter
import com.example.tamangfood.ui.homepage.adapter.RestaurantAdapter
import com.example.tamangfood.ui.homepage.model.Partners
import com.example.tamangfood.ui.signin.UsersData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

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
        observerDataHomePage()
    }

    private fun observerDataHomePage() {
        homeViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            Config.showProgressBar(binding.progress, isLoading)
        }
        homeViewModel.partnersList.observe(viewLifecycleOwner) { partnersList ->
            val listSlider = partnersList.flatMap {
                it.slider!!
            }
            pagerAdapter = PagerAdapter(listSlider, requireContext())
            binding.viewPager.adapter = pagerAdapter
            binding.indicator.setViewPager(binding.viewPager)

            restaurantFeaturedAdapter = RestaurantAdapter(partnersList, requireContext(), this)
            binding.rcFeaturedPartners.adapter = restaurantFeaturedAdapter
            restaurantBestPickAdapter = RestaurantAdapter(partnersList, requireContext(), this)
            binding.rcBestPick.adapter = restaurantBestPickAdapter

            allRestaurantAdapter = AllRestaurantAdapter(partnersList, requireContext())
            binding.rcAllRestaurant.adapter = allRestaurantAdapter
        }

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