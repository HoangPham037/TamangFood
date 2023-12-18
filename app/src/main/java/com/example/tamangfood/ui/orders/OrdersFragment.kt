package com.example.tamangfood.ui.orders

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentOrdersBinding
import com.example.tamangfood.ui.orders.adapter.OrderAdapter
import com.example.tamangfood.ui.singlerestaurent.addtoorders.RestaurantOrder


class OrdersFragment : BaseFragment<FragmentOrdersBinding>(
    FragmentOrdersBinding::inflate
) {

    private val ordersViewModel: OrdersViewModel by viewModels()
    private lateinit var orderAdapter: OrderAdapter

    override fun observerData() {
        super.observerData()
        orderAdapter = OrderAdapter(requireContext())
        binding.rcOrders.adapter = orderAdapter
        ordersViewModel.dataOrders.observe(viewLifecycleOwner) { listOrder ->
            if (listOrder != null) {
                orderAdapter.setList(listOrder as ArrayList<RestaurantOrder>)
            }
        }
        ordersViewModel.fetchDataFromFireStore()
    }

}