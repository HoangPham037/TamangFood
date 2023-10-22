package com.example.tamangfood.extensions

import android.content.Context
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun Context.showSmallLengthToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.showLongLengthToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.loadImg(imgUrl: Any?, view: AppCompatImageView) {
    Glide.with(this)
        .load(imgUrl)
        .into(view)
}