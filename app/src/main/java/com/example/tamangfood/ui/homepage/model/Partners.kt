package com.example.tamangfood.ui.homepage.model

import com.example.tamangfood.ui.singlerestaurent.model.Product

data class Partners(
    val listPage: List<ItemPager>? = null,
    val img: Int,
    val name: String,
    val description: String,
    val listType: List<String>?= null,
    val listProduct: List<Product>
)
