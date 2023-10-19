package com.example.tamangfood.ui.singlerestaurent.typefood

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentSeeFoodBinding
import com.example.tamangfood.ui.singlerestaurent.IOnItemClickListener
import com.example.tamangfood.ui.singlerestaurent.adapter.TypeFoodAdapter
import com.example.tamangfood.ui.singlerestaurent.model.Product

class SeeFoodFragment : BaseFragment<FragmentSeeFoodBinding>(
    FragmentSeeFoodBinding::inflate
),IOnItemClickListener{

    private val shareViewModel: ShareViewModel by activityViewModels()
    private lateinit var mostPopularAdapter: TypeFoodAdapter
    private lateinit var typeFoodAdapter: TypeFoodAdapter
    override fun observerData() {
        super.observerData()
        shareViewModel.selectRestaurant.observe(viewLifecycleOwner) {
            val listSeaFood = it.listProduct.filter { product ->
                product.type == "SeaFood"
            }
            typeFoodAdapter = TypeFoodAdapter(listSeaFood,this)
            binding.rcSeeFood.adapter = typeFoodAdapter
            val listMost = it.listProduct.take(2)
            mostPopularAdapter = TypeFoodAdapter(listMost, this)
            binding.rcMostPopulars.adapter = mostPopularAdapter
        }
    }

    override fun itemClick(product: Product) {
        shareViewModel.selectProduct.value = product
    }
}