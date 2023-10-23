package com.example.tamangfood.ui.search.searchchild

import android.app.Activity
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentSearchChildBinding
import com.example.tamangfood.extensions.addAfterTextChangeAction
import com.example.tamangfood.extensions.gone
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.extensions.visible
import com.example.tamangfood.ui.featuredpartners.OnItemClickListener
import com.example.tamangfood.ui.homepage.HomeViewModel
import com.example.tamangfood.ui.homepage.model.Partners
import com.example.tamangfood.ui.search.searchchild.adapter.RecentSearchesAdapter
import com.example.tamangfood.ui.search.searchchild.adapter.SearchRestaurantAdapter
import com.example.tamangfood.ui.search.searchchild.local.model.RecentSearchesData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchChildFragment : BaseFragment<FragmentSearchChildBinding>(
    FragmentSearchChildBinding::inflate
), OnItemClickListener {

    private val homeViewModel: HomeViewModel by viewModels()
    private val shareViewModel: ShareViewModel by activityViewModels()
    private val recentSearchesViewModel: RecentSearchesViewModel by viewModels{
        SavedStateViewModelFactory(requireActivity().application, this)
    }
    private lateinit var recentSearchesAdapter: RecentSearchesAdapter
    private lateinit var searchRestaurantAdapter: SearchRestaurantAdapter
    private lateinit var searchRestaurantAdapter2: SearchRestaurantAdapter

    override fun setUpView() {
        super.setUpView()
        binding.edtSearch.addAfterTextChangeAction { text ->
            handleTextChange(text)
        }
        binding.edtSearch.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                hideShowLayoutHistory()
            } else {
                binding.layoutHistory.root.gone()
            }
        }

    }

    private fun hideShowLayoutHistory() {
        searchRestaurantAdapter2 = SearchRestaurantAdapter(this)
        recentSearchesViewModel.recentSearches.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()){
                binding.layoutHistory.root.gone()
            }else {
                binding.layoutHistory.root.visible()
                binding.layoutHistory.rcRecentSearches.adapter = searchRestaurantAdapter2
            }
        }
        recentSearchesViewModel.fetchRecentSearch()
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.tvAction.setSafeOnClickListener {
            val imm =
                requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.edtSearch.windowToken, 0)
            binding.edtSearch.clearFocus()
            binding.edtSearch.text.clear()
            binding.tvAction.gone()
        }
    }

    private fun handleTextChange(text: String) {
        searchRestaurantAdapter = SearchRestaurantAdapter(this)
        binding.rcResult.adapter = searchRestaurantAdapter
        val textKeyword = text.trim()
        val isEmpty = TextUtils.isEmpty(textKeyword)
        val isFocus = binding.edtSearch.isFocused
        if (isEmpty) {
            binding.tvAction.gone()
            if (isFocus) {

            } else {
            }
        } else {
           homeViewModel.partnersList.observe(viewLifecycleOwner) {listPartners->

               val list = listPartners.filter { partners -> partners.name!!.lowercase().contains(text.lowercase(),ignoreCase = true) }
               searchRestaurantAdapter.updateList(list)
           }
            binding.tvAction.visible()

        }
    }

    override fun onItemClick(partners: Partners) {
        val name = partners.name
        val search = RecentSearchesData(name)
        shareViewModel.sendNameRestaurant.postValue(partners.name)
        recentSearchesViewModel.insertRecentSearches(search)
        findNavController().navigate(R.id.action_searchChildFragment_to_resultSearchingFragment)
    }
}