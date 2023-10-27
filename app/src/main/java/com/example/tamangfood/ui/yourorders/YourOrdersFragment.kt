package com.example.tamangfood.ui.yourorders

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.common.Constants
import com.example.tamangfood.common.Constants.SharePreference.DEFAULT_VALUE
import com.example.tamangfood.common.Constants.SharePreference.KEY_ID_RESTAURANT
import com.example.tamangfood.databinding.FragmentYourOrdersBinding
import com.example.tamangfood.extensions.convertToStringWithAUD
import com.example.tamangfood.extensions.get
import com.example.tamangfood.extensions.getMySharedPreferences
import com.example.tamangfood.extensions.put
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.ui.orders.adapter.OrdersAdapter
import com.example.tamangfood.ui.orders.model.OrderData
import com.example.tamangfood.ui.signin.UsersData
import com.example.tamangfood.ui.singlerestaurent.addtoorders.RestaurantOrder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Objects

class YourOrdersFragment : BaseFragment<FragmentYourOrdersBinding>(
    FragmentYourOrdersBinding::inflate
) {
    private val shareViewModel: ShareViewModel by activityViewModels()
    private lateinit var ordersAdapter: OrdersAdapter
    private lateinit var restaurantOrder: RestaurantOrder
    private val rootRef = FirebaseFirestore.getInstance()
    private val user = FirebaseAuth.getInstance()
    override fun observerData() {
        super.observerData()
        val sharedPreferences = requireContext().getMySharedPreferences()
        ordersAdapter = OrdersAdapter(shareViewModel.menus)
        binding.rcYourOrders.adapter = ordersAdapter

        val subTotal = shareViewModel.menus.map {
            it.total
        }.reduce { acc, fl -> acc + fl }
        binding.tvSubtotal.text = subTotal.convertToStringWithAUD()
        val restaurantClick = shareViewModel.selectRestaurant.value
        restaurantOrder = RestaurantOrder(restaurantClick, shareViewModel.menus)
        Log.d("Hoang.pv@extremevn.com", "restaurantOrder: ${restaurantClick?.id}")
        Log.d("Hoang.pv@extremevn.com", "sharedPreferences: ${sharedPreferences.get(KEY_ID_RESTAURANT, DEFAULT_VALUE)}")

        if (restaurantClick?.id != sharedPreferences.get(KEY_ID_RESTAURANT, DEFAULT_VALUE)){
            shareViewModel.restaurant.add(restaurantOrder)
            sharedPreferences.put(KEY_ID_RESTAURANT, restaurantClick?.id)
        }else {
            shareViewModel.restaurant.clear()
            shareViewModel.restaurant.add(restaurantOrder)
        }


        shareViewModel.restaurant.map {

            Log.d("Hoang.pv@extremevn.com", "restaurantOrder: ${it.restaurant?.name}")
        }
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.imgBack.setSafeOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnContinue.setSafeOnClickListener {
            val cartMap = hashMapOf(
                "cart" to shareViewModel.menus
            )
            val cartRef = rootRef.collection("users").document(user.uid.toString()).collection("carts").document()
            cartRef.set(cartMap)
            findNavController().navigate(R.id.action_yourOrdersFragment_to_addPaymentFragment)
        }
    }
}