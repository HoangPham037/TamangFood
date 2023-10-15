package com.example.tamangfood.ui.homepage.model

data class Partners(
    val listPage: List<ItemPager>? = null,
    val img: Int,
    val name: String,
    val description: String,
    val listType: List<String>?= null
)
