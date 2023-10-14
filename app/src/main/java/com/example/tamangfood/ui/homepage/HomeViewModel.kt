package com.example.tamangfood.ui.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tamangfood.R
import com.example.tamangfood.ui.homepage.model.Partners

class HomeViewModel : ViewModel() {
    private val _dataSumDistance = MutableLiveData<List<Partners>>()
    val dataSumDistance: LiveData<List<Partners>> get() = _dataSumDistance

    fun fetchListDistance() {
        _dataSumDistance.value = setListStepDaily()
    }

}

private fun setListStepDaily(): List<Partners> {
    val dailyOne = Partners(R.drawable.image_test, "Caffe house", "so1 pham van dong")
    val dailyTwo = Partners(R.drawable.image_test_two, "Caffe house", "so 21 co nhue")
    return listOf(
        dailyOne, dailyTwo
    )
}