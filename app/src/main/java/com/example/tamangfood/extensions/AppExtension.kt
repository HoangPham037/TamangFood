package com.example.tamangfood.extensions

import android.content.Context
import android.content.SharedPreferences
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.content.edit

fun EditText.addAfterTextChangeAction(textChangeAction: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            textChangeAction(s?.toString().orEmpty())
        }

    })
}

fun Context.getMySharedPreferences(): SharedPreferences {
    return getSharedPreferences("MyRefs", Context.MODE_PRIVATE)
}
fun SharedPreferences.putString(key:String, value: String) {
    edit().putString(key, value).apply()
}

fun SharedPreferences.getStrings(key: String, defaultValue: String) :String {
    return getString(key, defaultValue)?: defaultValue
}

fun SharedPreferences.putInt(key:String, value: Int) {
    edit().putInt(key, value).apply()
}

fun SharedPreferences.getInts(key: String, defaultValue: Int) :Int {
    return getInt(key, defaultValue)?: defaultValue
}

fun <T> SharedPreferences.put(key:String,t: T) {
    when(t) {
        is Int -> putInt(key, t)
        is String -> putString(key,t)
    }
}

fun <T> SharedPreferences.get(key:String, defaultValue: T) : T {
    return when(defaultValue) {
        is Int -> getInts(key, defaultValue) as T
        is String -> getStrings(key, defaultValue) as T
        else -> throw IllegalArgumentException("Unsupported data type")
    }
}
