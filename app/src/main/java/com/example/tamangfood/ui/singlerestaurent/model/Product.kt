package com.example.tamangfood.ui.singlerestaurent.model

data class Product(
    val img: Int,
    val name: String,
    val description: String,
    val price: Float,
    val listType: List<String>,
    val type: String
    )
