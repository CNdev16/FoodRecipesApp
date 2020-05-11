package com.example.core.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.util.*

abstract class BaseConverter <T>{
    @TypeConverter
    abstract fun fromString(value: String): List<T>

    @TypeConverter
    fun fromArrayList(value: List<T>): String{
        val json = Gson().toJson(value)
        return json ?: ""
    }
}