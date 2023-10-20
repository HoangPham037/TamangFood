package com.example.tamangfood.extensions

import android.content.Context
import android.widget.Toast

fun Context.showSmallLengthToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.showLongLengthToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}