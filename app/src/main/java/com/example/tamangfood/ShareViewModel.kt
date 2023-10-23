package com.example.tamangfood

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tamangfood.ui.addpayment.model.PaymentData
import com.example.tamangfood.ui.homepage.model.Partners
import com.example.tamangfood.ui.orders.model.OrderData
import com.example.tamangfood.ui.singlerestaurent.model.Product

class ShareViewModel : ViewModel() {
    val selectRestaurant = MutableLiveData<Partners>()
    val password = MutableLiveData<String>()
    val selectProduct = MutableLiveData<Product>()
    val addToOrder = MutableLiveData<List<OrderData>>()
    val addPayment = MutableLiveData<PaymentData>()
    val sendNameRestaurant = MutableLiveData<String>()
    val sendListNameRestaurant = MutableLiveData<List<String>>()
}