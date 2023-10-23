package com.example.tamangfood.ui.search.resultsearching

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentResultSearchingBinding
import com.example.tamangfood.ui.featuredpartners.OnItemClickListener
import com.example.tamangfood.ui.homepage.HomeViewModel
import com.example.tamangfood.ui.homepage.adapter.RestaurantAdapter
import com.example.tamangfood.ui.homepage.model.Partners
import com.example.tamangfood.ui.singlerestaurent.adapter.FeaturedItemAdapter


class ResultSearchingFragment : BaseFragment<FragmentResultSearchingBinding>(
    FragmentResultSearchingBinding::inflate
), OnItemClickListener {
private val shareViewModel: ShareViewModel by activityViewModels()
private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var restaurantAdapter: RestaurantAdapter
    private var keywordSearch = ""
    override fun observerData() {
        super.observerData()
        shareViewModel.sendNameRestaurant.observe(viewLifecycleOwner) {
            binding.edtSearch.setText(it)
            keywordSearch = it
        }
        homeViewModel.partnersList.observe(viewLifecycleOwner) {
            val list = it.filter { partners -> partners.name!!.lowercase().contains(keywordSearch.lowercase(),ignoreCase = true) }
            restaurantAdapter = RestaurantAdapter(list, requireContext(),this )
            binding.rcResult.adapter = restaurantAdapter
        }
    }

    override fun onItemClick(partners: Partners) {
        shareViewModel.selectRestaurant.postValue(partners)
        findNavController().navigate(R.id.action_resultSearchingFragment_to_singleRestaurantFragment)
    }

}