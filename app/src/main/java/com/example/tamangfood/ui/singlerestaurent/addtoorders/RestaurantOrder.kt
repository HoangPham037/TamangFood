package com.example.tamangfood.ui.singlerestaurent.addtoorders

import com.example.tamangfood.ui.homepage.model.Partners
import com.example.tamangfood.ui.orders.model.Cart
import com.example.tamangfood.ui.orders.model.OrderData

data class RestaurantOrder(
    val restaurant: Partners?=null,
    val listOrder:List<OrderData>?= emptyList()
)
