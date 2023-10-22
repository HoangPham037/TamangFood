package com.example.tamangfood.ui.search

import android.view.WindowManager.LayoutParams
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentSearchBinding
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.extensions.showLongLengthToast
import com.example.tamangfood.ui.featuredpartners.OnItemClickListener
import com.example.tamangfood.ui.homepage.HomeViewModel
import com.example.tamangfood.ui.homepage.model.Partners
import com.example.tamangfood.ui.search.adapter.TopRestaurantAdapter

class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate
), OnItemClickListener {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var topRestaurantAdapter: TopRestaurantAdapter
    private val shareViewModel: ShareViewModel by activityViewModels()
    override fun observerData() {
        super.observerData()
//        homeViewModel.dataAllRestaurant.observe(viewLifecycleOwner) { listPartners ->
//            val topRestaurant = listPartners.filter {
//                it.rating!! > 4.5f
//            }
//            topRestaurantAdapter = TopRestaurantAdapter(topRestaurant, this, requireContext())
//            binding.rcTopRestaurant.adapter = topRestaurantAdapter
//        }
//        homeViewModel.fetchListAllRestaurant()
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.layoutSearch.setSafeOnClickListener {

        }

    }


    override fun onItemClick(partners: Partners) {
        shareViewModel.selectRestaurant.value = partners
        findNavController().navigate(R.id.action_searchFragment_to_singleRestaurantFragment)
    }
}