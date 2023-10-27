package com.example.tamangfood.ui.homepage.model

import com.example.tamangfood.ui.singlerestaurent.model.Product

data class Partners(
    val id: Int?= 0,
    val slider: List<ItemPager>? = emptyList(),
    val imgUrl: String? = "",
    val name: String? = "",
    val description: String? = "",
    val product: List<Product>? = emptyList(),
    val rating: Float? = 0f,
    val country: List<String>? = emptyList(),
    val currency: String?=""
)
