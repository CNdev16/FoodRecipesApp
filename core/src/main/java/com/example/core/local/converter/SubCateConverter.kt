package com.example.core.local.converter

import androidx.room.TypeConverter
import com.example.core.data.ResultResponse
import com.example.core.data.SubCate
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class SubCateConverter : BaseConverter<SubCate>() {
    @TypeConverter
    override fun fromString(value: String): List<SubCate> {
        val json = object : TypeToken<List<SubCate>>() {}.type
        return Gson().fromJson<List<SubCate>>(value, json)
    }
}