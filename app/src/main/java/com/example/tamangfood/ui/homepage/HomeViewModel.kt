package com.example.tamangfood.ui.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tamangfood.R
import com.example.tamangfood.ui.homepage.model.ItemPager
import com.example.tamangfood.ui.homepage.model.Partners

class HomeViewModel : ViewModel() {
    private val _dataFeaturedPartners = MutableLiveData<List<Partners>>()
    val dataFeaturedPartners: LiveData<List<Partners>> get() = _dataFeaturedPartners
    fun fetchListFeaturedPartners() {
        _dataFeaturedPartners.value = setListFeaturedPartners()
    }

    private val _dataBestPickRestaurant = MutableLiveData<List<Partners>>()
    val dataBestPickRestaurant: LiveData<List<Partners>> get() = _dataBestPickRestaurant
    fun fetchListBestPickRestaurant() {
        _dataBestPickRestaurant.value = setListBestPickRestaurant()
    }

    private val _dataAllRestaurant = MutableLiveData<List<Partners>>()
    val dataAllRestaurant: LiveData<List<Partners>> get() = _dataAllRestaurant

    fun fetchListAllRestaurant() {
        _dataAllRestaurant.value = setListAllRestaurant()
    }

    private val _dataPager = MutableLiveData<List<ItemPager>>()
    val dataPager: LiveData<List<ItemPager>> get() = _dataPager
    fun fetchDataPager() {
        _dataPager.value = setListPager1()
    }
}

private fun setListFeaturedPartners(): List<Partners> {
    val dailyOne = Partners(
        null, R.drawable.image_test, "Caffe house", "so1 pham van dong",
        setListType()
    )
    val dailyTwo = Partners(
        null, R.drawable.image_test_two, "Caffe house", "so 21 co nhue",
        setListType()
    )
    return listOf(
        dailyOne, dailyTwo
    )
}

private fun setListBestPickRestaurant(): List<Partners> {
    val dailyOne = Partners(
        null, R.drawable.best_pick_one, "Caffe house", "so1 pham van dong",
        setListType()
    )
    val dailyTwo = Partners(
        null, R.drawable.best_pick_two, "Pizza Viet Nam", "so 21 co nhue",
        setListType()
    )
    return listOf(
        dailyOne, dailyTwo
    )
}

private fun setListType(): List<String> {
    return listOf("$$", "Ha Noi", "Da Nang", "Deshi food")
}

private fun setListPager1(): List<ItemPager> {
    val pagerOne = ItemPager(R.drawable.image_test)
    val pagerTwo = ItemPager(R.drawable.image_test_two)
    val pagerThree = ItemPager(R.drawable.image_test_three)
    val pagerFour = ItemPager(R.drawable.image_test)
    val pagerFive = ItemPager(R.drawable.image_test_three)
    return listOf(
        pagerOne, pagerTwo, pagerThree, pagerFour, pagerFive
    )
}

private fun setListPager2(): List<ItemPager> {
    val pagerOne = ItemPager(R.drawable.image_header)
    val pagerTwo = ItemPager(R.drawable.image_test_two)
    val pagerThree = ItemPager(R.drawable.image_test)
    val pagerFour = ItemPager(R.drawable.image_test_two)
    val pagerFive = ItemPager(R.drawable.image_test)
    return listOf(
        pagerOne, pagerTwo, pagerThree, pagerFour, pagerFive
    )
}

private fun setListPager3(): List<ItemPager> {
    val pagerOne = ItemPager(R.drawable.image_test_two)
    val pagerTwo = ItemPager(R.drawable.image_test)
    val pagerThree = ItemPager(R.drawable.image_test_three)
    val pagerFour = ItemPager(R.drawable.image_test)
    val pagerFive = ItemPager(R.drawable.image_test_three)
    return listOf(
        pagerOne, pagerTwo, pagerThree, pagerFour, pagerFive
    )
}

private fun setListAllRestaurant(): List<Partners> {
    val restaurantOne =
        Partners(setListPager1(), R.drawable.image_test, "Pizza hurt", "", setListType())
    val restaurantTwo =
        Partners(setListPager2(), R.drawable.image_test, "Caffe house", "", setListType())
    val restaurantThree =
        Partners(setListPager3(), R.drawable.image_test, "Vina Caffe", "", setListType())
    return listOf(restaurantOne, restaurantTwo, restaurantThree)
}