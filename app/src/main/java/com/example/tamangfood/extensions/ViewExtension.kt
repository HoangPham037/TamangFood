package com.example.tamangfood.extensions

import android.view.View
import androidx.navigation.findNavController

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.goneIf(goneIf: Boolean) {
    visibility = if (goneIf) View.GONE else View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.setSafeOnClickListener(
    interval: Long = 500L,
    onClick: (View) -> Unit
) {
    setOnClickListener {
        onClick(this)
        isClickable = false
        postDelayed({isClickable = true}, interval)
    }
}


