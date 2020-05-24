package com.example.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.data.Favorite
import com.example.core.data.RecipePosts
import com.example.core.data.CountryCategory
import com.example.core.data.MenuCategory
import com.example.core.local.converter.DateTimeConverter
import com.example.core.local.converter.PostsConverter
import com.example.core.local.converter.SubCateConverter

@Database(
    entities = [Favorite::class, CountryCategory::class, MenuCategory::class, RecipePosts::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [PostsConverter::class, SubCateConverter::class, DateTimeConverter::class])
abstract class AppDataBase : RoomDatabase() {

    abstract fun appDataBaseDao(): AppDataBaseDao

}