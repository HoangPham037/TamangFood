package com.example.tamangfood.ui.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tamangfood.ui.orders.model.OrderData

class OrdersViewModel: ViewModel() {
    private val _dataOrders = MutableLiveData<List<OrderData>>()
    val dataOrders: LiveData<List<OrderData>> get() = _dataOrders

    fun setListOrders(listOrders: List<OrderData>) {
        _dataOrders.value = listOrders
    }
}