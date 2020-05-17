package com.example.core.local.converter

import androidx.room.TypeConverter
import com.example.core.data.MenuCategory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SubCateConverter : BaseConverter<MenuCategory>() {
    @TypeConverter
    override fun fromString(value: String): List<MenuCategory> {
        val json = object : TypeToken<List<MenuCategory>>() {}.type
        return Gson().fromJson<List<MenuCategory>>(value, json)
    }
}