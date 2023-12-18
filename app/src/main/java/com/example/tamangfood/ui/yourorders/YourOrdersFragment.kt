package com.example.tamangfood.ui.yourorders

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.common.Constants.SharePreference.DEFAULT_VALUE
import com.example.tamangfood.common.Constants.SharePreference.KEY_ID_RESTAURANT
import com.example.tamangfood.databinding.FragmentYourOrdersBinding
import com.example.tamangfood.extensions.convertToStringWithAUD
import com.example.tamangfood.extensions.get
import com.example.tamangfood.extensions.getMySharedPreferences
import com.example.tamangfood.extensions.put
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.ui.orders.adapter.CartAdapter
import com.example.tamangfood.ui.singlerestaurent.addtoorders.RestaurantOrder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class YourOrdersFragment : BaseFragment<FragmentYourOrdersBinding>(
    FragmentYourOrdersBinding::inflate
) {
    private val shareViewModel: ShareViewModel by activityViewModels()
    private lateinit var ordersAdapter: CartAdapter
    private lateinit var restaurantOrder: RestaurantOrder
    private val rootRef = FirebaseFirestore.getInstance()
    private val user = FirebaseAuth.getInstance()
    override fun observerData() {
        super.observerData()
        val sharedPreferences = requireContext().getMySharedPreferences()
        ordersAdapter = CartAdapter(shareViewModel.menus)
        binding.rcYourOrders.adapter = ordersAdapter

        val subTotal = shareViewModel.menus.map {
            it.total
        }.reduce { acc, fl -> acc!! + fl!!}
        binding.tvSubtotal.text = subTotal?.convertToStringWithAUD()
        val restaurantClick = shareViewModel.selectRestaurant.value
        restaurantOrder = RestaurantOrder(restaurantClick, shareViewModel.menus)


        if (restaurantClick?.id != sharedPreferences.get(KEY_ID_RESTAURANT, DEFAULT_VALUE)) {
            shareViewModel.restaurant.add(restaurantOrder)
            sharedPreferences.put(KEY_ID_RESTAURANT, restaurantClick?.id)
        } else {
            shareViewModel.restaurant.clear()
            shareViewModel.restaurant.add(restaurantOrder)
        }
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.imgBack.setSafeOnClickListener {
            findNavController().navigateUp()
        }
        binding.addMoreItem.setSafeOnClickListener {
            findNavController().navigate(R.id.action_yourOrdersFragment_to_singleRestaurantFragment)
        }
        binding.btnContinue.setSafeOnClickListener {


            val cartRef = rootRef.collection("users").document(user.uid.toString())
                .collection("restaurantOrder").document()
            cartRef.set(restaurantOrder)
            findNavController().navigate(R.id.action_yourOrdersFragment_to_addPaymentFragment)
        }
    }
}