package com.example.tamangfood

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tamangfood.ui.homepage.model.Partners
import com.example.tamangfood.ui.singlerestaurent.model.Product

class ShareViewModel : ViewModel() {
    val selectRestaurant = MutableLiveData<Partners>()
    val password = MutableLiveData<String>()
    val selectProduct = MutableLiveData<Product>()
}