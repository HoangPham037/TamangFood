package com.example.tamangfood.ui.singlerestaurent.addtoorders

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.common.Constants
import com.example.tamangfood.common.Constants.SharePreference.KEY_ID_PRODUCT
import com.example.tamangfood.common.Constants.SharePreference.KEY_ID_RESTAURANT
import com.example.tamangfood.databinding.FragmentAddToOrdersBinding
import com.example.tamangfood.extensions.convertToString
import com.example.tamangfood.extensions.convertToStringInQuotes
import com.example.tamangfood.extensions.get
import com.example.tamangfood.extensions.getMySharedPreferences
import com.example.tamangfood.extensions.loadImg
import com.example.tamangfood.extensions.put
import com.example.tamangfood.extensions.putInt
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.ui.homepage.model.Partners
import com.example.tamangfood.ui.orders.model.OrderData
import com.example.tamangfood.ui.singlerestaurent.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

class AddToOrdersFragment : BaseFragment<FragmentAddToOrdersBinding>(
    FragmentAddToOrdersBinding::inflate
) {

    private val shareViewModel: ShareViewModel by activityViewModels()
    private val addToOrdersViewModel: AddToOrdersViewModel by viewModels()
    private lateinit var products: Product
    private var qualitys: Int = 0
    private lateinit var partners: Partners
    override fun observerData() {
        super.observerData()
        shareViewModel.selectRestaurant.observe(viewLifecycleOwner) { partners ->
            this.partners = partners
        }
        shareViewModel.selectProduct.observe(viewLifecycleOwner) { product ->
            products = product
            with(binding) {
                requireContext().loadImg(product.imgUrl, imgProduct)
                tvNameProduct.text = product.name
                tvDesProduct.text = product.description
                addToOrdersViewModel.qualityData.observe(viewLifecycleOwner) { quality ->
                    qualitys = quality
                    if (quality < 10) {
                        tvQuantity.text = quality.convertToString()
                    } else {
                        tvQuantity.text = quality.toString()
                    }

                    binding.tvSumPrice.text =
                        (product.price!! * quality).convertToStringInQuotes()
                }
                tvCurrency.text = product.currency
                tvTypeOne.text = product.country
            }
        }
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.imgBack.setSafeOnClickListener {
            findNavController().navigateUp()
        }
        binding.imgPlus.setSafeOnClickListener {
            addToOrdersViewModel.plusQuality()
        }
        binding.imgMinus.setSafeOnClickListener {
            addToOrdersViewModel.minusQuality()
        }
        binding.btnAddToOrder.setSafeOnClickListener {
            handleAddCart()
//            val userRef = rootRef.collection("restaurantOrder")
//            userRef.add(restaurantOrder)
//                .addOnSuccessListener {
//                    Timber.tag("SUCCESS").i("update list payment success")
//                }
//                .addOnFailureListener {
//                    Timber.tag("SUCCESS").i("update list payment failed")
//                }
            findNavController().navigate(R.id.action_addToOrdersFragment_to_yourOrdersFragment)
        }
    }
    private fun handleAddCart() {
        val sharedPreferences = requireContext().getMySharedPreferences()
        val restaurantId = shareViewModel.selectRestaurant.value?.id
        val savedRestaurantId = sharedPreferences.get(KEY_ID_RESTAURANT, 0)
        val orderData = OrderData(products, qualitys, products.price!! * qualitys)
        if (restaurantId != savedRestaurantId) {
            shareViewModel.menus.clear()
            shareViewModel.menus.add(orderData)
            sharedPreferences.put(KEY_ID_PRODUCT, products.id)
            sharedPreferences.put(KEY_ID_RESTAURANT, restaurantId)
        } else {
            sharedPreferences.put(KEY_ID_PRODUCT, products.id)
            shareViewModel.menus.add(orderData)
        }
    }
}