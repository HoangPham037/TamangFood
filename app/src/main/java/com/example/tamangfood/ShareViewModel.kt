package com.example.tamangfood

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tamangfood.ui.homepage.model.Partners

class ShareViewModel : ViewModel() {
    val selectRestaurant = MutableLiveData<Partners>()
    val password = MutableLiveData<String>()
}