package com.example.tamangfood.ui.singlerestaurent.model

data class Product(
    val id: Int?=0,
    val imgUrl: String? = "",
    val name: String? = "",
    val description: String? = "",
    val price: Float? = 0f,
    val currency: String? = "",
    val type: String? = "",
    val country: String?= ""
)
