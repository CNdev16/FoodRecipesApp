package com.example.allrecipesfree_foodrecipesapp.utility

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

//convert date time.
fun String.formatDateTime():String{
    return try {
        this.replace("T", " ")
    }catch (e: Exception){
        this
    }
}

fun View.hideKeyboard() {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}