package com.example.tamangfood.ui.singlerestaurent.addtoorders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
}