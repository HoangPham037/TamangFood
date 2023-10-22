package com.example.tamangfood.ui.search.searchchild

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import com.example.tamangfood.R
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentSearchChildBinding
import com.example.tamangfood.extensions.addAfterTextChangeAction
import com.example.tamangfood.extensions.gone
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.extensions.showLongLengthToast
import com.example.tamangfood.extensions.visible
import com.example.tamangfood.ui.homepage.HomeViewModel
import com.example.tamangfood.ui.homepage.model.Partners
import org.w3c.dom.Text

class SearchChildFragment : BaseFragment<FragmentSearchChildBinding>(
    FragmentSearchChildBinding::inflate
) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val searchChildViewModel: SearchChildViewModel by viewModels()
    private var listRestaurant = ArrayList<Partners>()
    private lateinit var recentSearchesAdapter: RecentSearchesAdapter

    override fun setUpView() {
        super.setUpView()
        recentSearchesAdapter = RecentSearchesAdapter()
        binding.rcResult.adapter = recentSearchesAdapter
        binding.edtSearch.addAfterTextChangeAction { text ->
            handleTextChange(text)
        }
        binding.edtSearch.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
//            if (hasFocus) {
//                binding.layoutHistory.root.visible()
//            } else {
//                binding.layoutHistory.root.gone()
//            }
        }

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
        recentSearchesAdapter = RecentSearchesAdapter()
        binding.rcResult.adapter = recentSearchesAdapter
        val textKeyword = text.trim()
        val isEmpty = TextUtils.isEmpty(textKeyword)
        val isFocus = binding.edtSearch.isFocused
        if (isEmpty) {
            binding.tvAction.gone()
            if (isFocus) {

            } else {
            }
        } else {
            searchChildViewModel.searchData(text)
            searchChildViewModel.getSearchResult().observe(viewLifecycleOwner) { searchData ->
                recentSearchesAdapter.updateData(searchData)
            }
            binding.tvAction.visible()
        }
    }
}