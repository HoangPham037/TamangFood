package com.example.tamangfood.common

import android.content.Context
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.tamangfood.R
import com.example.tamangfood.extensions.gone
import com.example.tamangfood.extensions.visible

object Config {
    fun showProgressBar(view: View, visibleIf: Boolean) {
        if (visibleIf) {
            view.visible()
        } else {
            view.gone()
        }
    }

    fun showHidePassword(view: View, edt: View, context: Context) {
        if ((edt as EditText).transformationMethod is PasswordTransformationMethod) {
            edt.transformationMethod = HideReturnsTransformationMethod.getInstance()
            (view as ImageView).setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_visible
                )
            )
        } else {
            (view as ImageView).setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_invisible
                )
            )
            edt.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }


    fun setIndicator(view: ViewGroup, pageSize: Int, context: Context) {
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)

        for (i in 0 until pageSize) {
            val imageView = ImageView(context)
            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    context, R.drawable.indicator_inactive
                )
            )
            imageView.layoutParams = layoutParams
            view.addView(imageView)
        }
    }

    fun setCurrentIndicator(index: Int, view: ViewGroup, context: Context) {
        val childCount = view.childCount
        for (i in 0 until childCount) {
            val image = view.getChildAt(i) as ImageView
            if (i == index) {
                image.setImageDrawable(
                    ContextCompat.getDrawable(
                        context, R.drawable.indicator_active
                    )
                )
            } else {
                image.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }

    }
}