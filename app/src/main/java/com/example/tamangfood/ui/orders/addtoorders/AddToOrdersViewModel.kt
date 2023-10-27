package com.example.tamangfood.ui.orders.addtoorders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tamangfood.ui.orders.model.Cart

class AddToOrdersViewModel : ViewModel() {
    private var quality = 1
    private val _qualityData = MutableLiveData<Int>()
    val qualityData: LiveData<Int> get() = _qualityData

    fun plusQuality() {
        quality++
        _qualityData.value = quality
    }

    fun minusQuality() {
        if (quality == 1) {
            quality = 1
            _qualityData.value = quality

        } else {
            quality--
            _qualityData.value = quality
        }
    }

    private val _dataListOrder = MutableLiveData<List<Cart>>()
    val dataListCart : LiveData<List<Cart>> get() = _dataListOrder

    fun setDataListCart(listCart: List<Cart>) {
        _dataListOrder.value = listCart
    }
}