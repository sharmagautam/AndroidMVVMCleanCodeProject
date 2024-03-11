package com.example.androidmvvmcleancodeproject.ui.utils

import android.content.Context
import android.widget.Toast

object Helpers {
    fun Context.toast(message: String){
        Toast.makeText(this, message,Toast.LENGTH_LONG).show()
    }
}