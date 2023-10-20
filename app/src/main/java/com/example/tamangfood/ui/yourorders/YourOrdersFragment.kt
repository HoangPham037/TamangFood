package com.example.tamangfood.ui.yourorders

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentYourOrdersBinding
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.ui.orders.adapter.OrdersAdapter

class YourOrdersFragment : BaseFragment<FragmentYourOrdersBinding>(
    FragmentYourOrdersBinding::inflate
) {
    private val shareViewModel: ShareViewModel by activityViewModels()
    private lateinit var ordersAdapter: OrdersAdapter
    override fun observerData() {
        super.observerData()
        shareViewModel.addToOrder.observe(viewLifecycleOwner) {
            ordersAdapter = OrdersAdapter(it)
            binding.rcYourOrders.adapter = ordersAdapter
        }
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.imgBack.setSafeOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnContinue.setSafeOnClickListener {
            findNavController().navigate(R.id.action_yourOrdersFragment_to_addPaymentFragment)
        }
    }
}