package com.example.tamangfood.ui.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tamangfood.ui.orders.model.OrderData
import com.example.tamangfood.ui.singlerestaurent.addtoorders.RestaurantOrder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor() : ViewModel() {
    private val db = Firebase.firestore
    private val userId = FirebaseAuth.getInstance().uid
    private val _dataOrders = MutableLiveData<List<RestaurantOrder>>()
    val dataOrders: LiveData<List<RestaurantOrder>> get() = _dataOrders


    fun fetchDataFromFireStore() {
        db.collection("users").document(userId.toString()).collection("restaurantOrder").get()
            .addOnSuccessListener { result ->
                val oderList = mutableListOf<RestaurantOrder>()
                for (document in result) {
                    val orderData = document.toObject(RestaurantOrder::class.java)
                    oderList.add(orderData)
                }
                _dataOrders.value = oderList
            }
    }

}