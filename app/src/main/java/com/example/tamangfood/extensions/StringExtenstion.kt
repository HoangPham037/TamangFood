package com.example.tamangfood.extensions

import android.util.Patterns

fun String.validateEmail(): Boolean =
    this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.validatePassword(): Boolean =
    this.isNotEmpty() && this.length > 8