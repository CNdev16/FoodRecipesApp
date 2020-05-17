package com.example.core.local.converter

import androidx.room.TypeConverter
import com.example.core.data.RecipePosts
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PostsConverter : BaseConverter<RecipePosts>() {
    @TypeConverter
    override fun fromString(value: String): List<RecipePosts> {
        val json = object : TypeToken<List<RecipePosts>>() {}.type
        return Gson().fromJson<List<RecipePosts>>(value, json)
    }
}