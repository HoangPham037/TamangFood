package com.example.tamangfood

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tamangfood.ui.addpayment.model.PaymentData
import com.example.tamangfood.ui.homepage.model.Partners
import com.example.tamangfood.ui.orders.model.Cart
import com.example.tamangfood.ui.orders.model.OrderData
import com.example.tamangfood.ui.singlerestaurent.addtoorders.RestaurantOrder
import com.example.tamangfood.ui.singlerestaurent.model.Product

class ShareViewModel : ViewModel() {
    val selectRestaurant = MutableLiveData<Partners>()
    val password = MutableLiveData<String>()
    val selectProduct = MutableLiveData<Product>()
    val addPayment = MutableLiveData<PaymentData>()
    val sendNameRestaurant = MutableLiveData<String>()
    val menus = mutableListOf<OrderData>()
    val restaurant = mutableListOf<RestaurantOrder>()
    val listNameHistory = MutableLiveData<List<String>>()
}