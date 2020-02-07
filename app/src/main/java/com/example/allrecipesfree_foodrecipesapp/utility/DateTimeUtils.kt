package com.example.allrecipesfree_foodrecipesapp.utility

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {
    fun convertDateTime(string: String): String {
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
        return sdf.format(string)
    }
}