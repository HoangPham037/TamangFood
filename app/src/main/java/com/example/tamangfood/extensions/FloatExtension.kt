package com.example.tamangfood.extensions

fun Float.convertToString() = String.format("%s%.2f%s", "($", this, ")")