package com.example.core.local.converter

import androidx.room.TypeConverter
import com.example.core.data.Posts
import com.example.core.data.ResultResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class PostsConverter : BaseConverter<Posts>() {
    @TypeConverter
    override fun fromString(value: String): List<Posts> {
        val json = object : TypeToken<List<Posts>>() {}.type
        return Gson().fromJson<List<Posts>>(value, json)
    }
}