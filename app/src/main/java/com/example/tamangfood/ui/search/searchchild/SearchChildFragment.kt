package com.example.tamangfood.ui.search.searchchild

import android.text.TextUtils
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.common.Config.hideKeyboard
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
    private val recentSearchesViewModel: RecentSearchesViewModel by viewModels()
    private var searchHistoryOnScrollListener: OnScrollListener? = null
    private var searchRestaurantOnScrollListener: OnScrollListener? = null

    private lateinit var recentSearchesAdapter: RecentSearchesAdapter
    private lateinit var searchRestaurantAdapter: SearchRestaurantAdapter
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
        recentSearchesAdapter = RecentSearchesAdapter()
        searchHistoryOnScrollListener = object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    hideKeyboard(requireContext(), binding.edtSearch)
                    binding.edtSearch.clearFocus()
                }
            }
        }
        binding.layoutHistory.rcRecentSearches.addOnScrollListener(searchHistoryOnScrollListener as OnScrollListener)
        binding.layoutHistory.rcRecentSearches.adapter = recentSearchesAdapter

        recentSearchesViewModel.recentSearches.observe(viewLifecycleOwner) { recentSearchesData ->
            if (recentSearchesData.isNullOrEmpty()) {
                binding.layoutHistory.root.gone()
                shareViewModel.listNameHistory.value = emptyList()
            } else {
                shareViewModel.listNameHistory.value = recentSearchesData.map { it.name.toString() }
                binding.layoutHistory.root.visible()
                recentSearchesAdapter.updateData(recentSearchesData as ArrayList<RecentSearchesData>)
                binding.layoutHistory.clearAll.setSafeOnClickListener {
                    clearALl()
                    binding.layoutHistory.root.gone()
                }
            }
        }
        recentSearchesViewModel.fetchRecentSearch()
    }

    private fun clearALl() {
        hideKeyboard(requireContext(), binding.edtSearch)
        binding.edtSearch.clearFocus()
        recentSearchesAdapter.clearData()
        recentSearchesViewModel.deleteAllRecentSearches()
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.tvAction.setSafeOnClickListener {
            binding.edtSearch.clearFocus()
            binding.edtSearch.text.clear()
            binding.tvAction.gone()
        }
    }

    private fun handleTextChange(text: String) {
        searchRestaurantAdapter = SearchRestaurantAdapter(this)
        searchRestaurantOnScrollListener = object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    hideKeyboard(requireContext(), binding.edtSearch)
                    binding.edtSearch.clearFocus()
                }
            }
        }
        binding.rcResult.addOnScrollListener(searchRestaurantOnScrollListener as OnScrollListener)
        binding.rcResult.adapter = searchRestaurantAdapter
        val textKeyword = text.trim()
        val isEmpty = TextUtils.isEmpty(textKeyword)
        val isFocus = binding.edtSearch.isFocused
        if (isEmpty) {
            binding.tvAction.gone()
            if (isFocus) {
                binding.layoutHistory.root.visible()
            }
        } else {
            binding.layoutHistory.root.gone()
            homeViewModel.partnersList.observe(viewLifecycleOwner) { listPartners ->
                val list = listPartners.filter { partners ->
                    partners.name!!.lowercase().contains(text.lowercase(), ignoreCase = true)
                }
                searchRestaurantAdapter.updateList(list)
            }
            binding.tvAction.visible()
        }
    }

    override fun onItemClick(partners: Partners) {
        handleSaveData(partners)
        findNavController().navigate(R.id.action_searchChildFragment_to_resultSearchingFragment)
    }

    private fun handleSaveData(partners: Partners) {
        val name = partners.name
        val search = RecentSearchesData(name)
        shareViewModel.sendNameRestaurant.postValue(partners.name)
        shareViewModel.listNameHistory.observe(viewLifecycleOwner) {
            if (it.isEmpty() || !it.contains(name)) {
                recentSearchesViewModel.insertRecentSearches(search)
            }
        }
    }
}