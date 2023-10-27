package com.example.tamangfood.ui.orders.model

import com.example.tamangfood.ui.singlerestaurent.model.Product

data class Cart(
    val product: Product? = null,
    val quality: Int ? = 0,
    val total: Float? = 0f
)
