package com.example.tamangfood.extensions

fun Float.convertToStringInQuotes() = String.format("%s%.2f%s", "($", this, ")")
fun Float.convertToStringWithAUD() = String.format("%s%.1f", "AUD$", this)

fun Float.convertToStringOneNumber() = String.format("%.1f", this)