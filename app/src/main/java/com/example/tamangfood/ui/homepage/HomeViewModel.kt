package com.example.tamangfood.ui.homepage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tamangfood.R
import com.example.tamangfood.ui.homepage.model.ItemPager
import com.example.tamangfood.ui.homepage.model.Partners
import com.example.tamangfood.ui.singlerestaurent.model.Product
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel : ViewModel() {

    private val fireStore = FirebaseFirestore.getInstance()
    val partnersList: MutableLiveData<List<Partners>> by lazy {
        MutableLiveData<List<Partners>>().apply {
            loadPartners()
        }
    }
    private fun loadPartners() {
        val partnersRef = fireStore.collection("restaurants")
        val partnersListTemp = mutableListOf<Partners>()

        partnersRef.get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    Log.d("2222", "${document.id} => ${document.data}")
                    val partner = document.toObject(Partners::class.java)
                    partnersListTemp.add(partner)
                }

                partnersList.value = partnersListTemp
            }
            .addOnFailureListener { exception ->
                // Xử lý lỗi
            }
    }
//    private val _dataFeaturedPartners = MutableLiveData<List<Partners>>()
//    val dataFeaturedPartners: LiveData<List<Partners>> get() = _dataFeaturedPartners
//    fun fetchListFeaturedPartners() {
//        _dataFeaturedPartners.value = setListFeaturedPartners()
//    }
//
//    private val _dataBestPickRestaurant = MutableLiveData<List<Partners>>()
//    val dataBestPickRestaurant: LiveData<List<Partners>> get() = _dataBestPickRestaurant
//    fun fetchListBestPickRestaurant() {
//        _dataBestPickRestaurant.value = setListBestPickRestaurant()
//    }
//
//    private val _dataAllRestaurant = MutableLiveData<List<Partners>>()
//    val dataAllRestaurant: LiveData<List<Partners>> get() = _dataAllRestaurant
//
//    fun fetchListAllRestaurant() {
//        _dataAllRestaurant.value = setListAllRestaurant()
//    }
//
//    private val _dataPager = MutableLiveData<List<ItemPager>>()
//    val dataPager: LiveData<List<ItemPager>> get() = _dataPager
//    fun fetchDataPager() {
//        _dataPager.value = setListPager1()
//    }
}

//private val setListType = listOf(
//    "$$", "Viet Nam"
//)
//
//private fun setListFeaturedProduct(): List<Product> {
//    val productOne = Product(
//        R.drawable.img_featured_item_one,
//        "Chow Fun",
//        "Shortbread, chocolate turtle cookies, and red velvet.",
//        10f,
//        setListType(),
//        "SeaFood"
//    )
//    val productTwo = Product(
//        R.drawable.img_featured_item_one,
//        "Dim SUm",
//        "Shortbread, chocolate turtle cookies, and red velvet.",
//        10f,
//        setListType(),
//        "Appetizers"
//    )
//    val productThree = Product(
//        R.drawable.item_featured_item_two,
//        "Fried Rice on Pot",
//        "Shortbread, chocolate turtle cookies, and red velvet.",
//        10f,
//        setListType(),
//        "SeaFood"
//    )
//    val productFour = Product(
//        R.drawable.item_featured_item_three,
//        "Oyster On Ice",
//        "Shortbread, chocolate turtle cookies, and red velvet.",
//        10f,
//        setListType(),
//        "SeaFood"
//    )
//    val productFive = Product(
//        R.drawable.item_featured_item_four,
//        "Oyster Dish",
//        "Shortbread, chocolate turtle cookies, and red velvet.",
//        10f,
//        setListType(),
//        "Appetizers"
//    )
//    return listOf(
//        productOne, productTwo, productThree, productFour, productFive
//    )
//}
//
//private fun setListFeaturedPartners(): List<Partners> {
//    val dailyOne = Partners(
//        setListPager1(), R.drawable.image_test, "Caffe house", "so1 pham van dong",
//        setListType(), setListFeaturedProduct()
//    )
//    val dailyTwo = Partners(
//        setListPager2(),
//        R.drawable.image_test_two,
//        "Pizza hurt",
//        "so 21 co nhue",
//        setListType(),
//        setListFeaturedProduct()
//    )
//    val dailyThree = Partners(
//        setListPager3(),
//        R.drawable.img_featured_item_one,
//        "Galdan",
//        "so 54 pham van bach",
//        setListType(),
//        setListFeaturedProduct()
//    )
//    val dailyFour = Partners(
//        setListPager1(),
//        R.drawable.img_item_featured_vertical,
//        "Mixue",
//        "so 12 duc thang",
//        setListType(),
//        setListFeaturedProduct()
//    )
//    return listOf(
//        dailyOne, dailyTwo, dailyThree, dailyFour
//    )
//}
//
//private fun setListBestPickRestaurant(): List<Partners> {
//    val dailyOne = Partners(
//        setListPager3(),
//        R.drawable.best_pick_one,
//        "Caffe house",
//        "so1 pham van dong",
//        setListType(),
//        setListFeaturedProduct()
//    )
//    val dailyTwo = Partners(
//        setListPager2(),
//        R.drawable.best_pick_two,
//        "Pizza Viet Nam",
//        "so 21 co nhue",
//        setListType(),
//        setListFeaturedProduct()
//    )
//    return listOf(
//        dailyOne, dailyTwo
//    )
//}
//
//private fun setListType(): List<String> {
//    return listOf("$$", "Ha Noi", "Da Nang", "Deshi food")
//}
//
//private fun setListPager1(): List<ItemPager> {
//    val pagerOne = ItemPager(R.drawable.image_test)
//    val pagerTwo = ItemPager(R.drawable.image_test_two)
//    val pagerThree = ItemPager(R.drawable.image_test_three)
//    val pagerFour = ItemPager(R.drawable.image_test)
//    val pagerFive = ItemPager(R.drawable.image_test_three)
//    return listOf(
//        pagerOne, pagerTwo, pagerThree, pagerFour, pagerFive
//    )
//}
//
//private fun setListPager2(): List<ItemPager> {
//    val pagerOne = ItemPager(R.drawable.image_header)
//    val pagerTwo = ItemPager(R.drawable.image_test_two)
//    val pagerThree = ItemPager(R.drawable.image_test)
//    val pagerFour = ItemPager(R.drawable.image_test_two)
//    val pagerFive = ItemPager(R.drawable.image_test)
//    return listOf(
//        pagerOne, pagerTwo, pagerThree, pagerFour, pagerFive
//    )
//}
//
//private fun setListPager3(): List<ItemPager> {
//    val pagerOne = ItemPager(R.drawable.image_test_two)
//    val pagerTwo = ItemPager(R.drawable.image_test)
//    val pagerThree = ItemPager(R.drawable.image_test_three)
//    val pagerFour = ItemPager(R.drawable.image_test)
//    val pagerFive = ItemPager(R.drawable.image_test_three)
//    return listOf(
//        pagerOne, pagerTwo, pagerThree, pagerFour, pagerFive
//    )
//}
//
//private fun setListAllRestaurant(): List<Partners> {
//    val restaurantOne =
//        Partners(
//            setListPager1(), R.drawable.image_test, "Pizza hurt", "", setListType(),
//            setListFeaturedProduct(), 4.3f
//        )
//    val restaurantTwo =
//        Partners(
//            setListPager2(), R.drawable.image_test, "Caffe house", "", setListType(),
//            setListFeaturedProduct(), 3.5f
//        )
//    val restaurantThree =
//        Partners(
//            setListPager3(), R.drawable.image_test, "Vina Caffe", "", setListType(),
//            setListFeaturedProduct(), 3.0f
//        )
//
//    val restaurantFour =
//        Partners(
//            setListPager1(), R.drawable.image_test, "Pizza hurt", "", setListType(),
//            setListFeaturedProduct(), 4.9f
//        )
//    val restaurantFive =
//        Partners(
//            setListPager2(), R.drawable.image_test, "Caffe house", "", setListType(),
//            setListFeaturedProduct(),3.9f
//        )
//    val restaurantSix =
//        Partners(
//            setListPager3(), R.drawable.image_test, "Vina Caffe", "", setListType(),
//            setListFeaturedProduct(), 4.9f
//        )
//
//    val restaurantSeven =
//        Partners(
//            setListPager1(), R.drawable.image_test, "Pizza hurt", "", setListType(),
//            setListFeaturedProduct(), 4.5f
//        )
//    val restaurantEight =
//        Partners(
//            setListPager2(), R.drawable.image_test, "Caffe house", "", setListType(),
//            setListFeaturedProduct(), 4.9f
//        )
//    val restaurantNight =
//        Partners(
//            setListPager3(), R.drawable.image_test, "Vina Caffe", "", setListType(),
//            setListFeaturedProduct(), 4.9f
//        )
//    val restaurantTen =
//        Partners(
//            setListPager1(), R.drawable.image_test, "Vina Caffe", "", setListType(),
//            setListFeaturedProduct(), 4.9f
//        )
//    return listOf(
//        restaurantOne,
//        restaurantTwo,
//        restaurantThree,
//        restaurantFour,
//        restaurantFive,
//        restaurantSix,
//        restaurantSeven,
//        restaurantEight,
//        restaurantNight,
//        restaurantTen
//    )
//}