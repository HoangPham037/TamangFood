package com.example.tamangfood.ui.singlerestaurent.typefood

import androidx.fragment.app.activityViewModels
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentAppetizersBinding
import com.example.tamangfood.ui.singlerestaurent.adapter.TypeFoodAdapter


class AppetizersFragment : BaseFragment<FragmentAppetizersBinding>(
    FragmentAppetizersBinding::inflate
) {

    private val shareViewModel: ShareViewModel by activityViewModels()
    private lateinit var mostPopularAdapter: TypeFoodAdapter
    private lateinit var typeFoodAdapter: TypeFoodAdapter
    override fun observerData() {
        super.observerData()
        shareViewModel.selectRestaurant.observe(viewLifecycleOwner) {
            val listSeaFood = it.listProduct.filter { product ->
                product.type == "Appetizers"
            }
            typeFoodAdapter = TypeFoodAdapter(listSeaFood)
            binding.rcAppetizers.adapter = typeFoodAdapter
            val listMost = it.listProduct.take(2)
            mostPopularAdapter = TypeFoodAdapter(listMost)
            binding.rcMostPopulars.adapter = mostPopularAdapter
        }
    }

}