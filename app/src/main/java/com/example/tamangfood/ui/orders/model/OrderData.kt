package com.example.tamangfood.ui.orders.model

import com.example.tamangfood.ui.singlerestaurent.model.Product

data class OrderData(
    val product: Product,
    val quality: Int,
    val total: Float
)
