package com.example.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.data.Favorite
import com.example.core.data.Posts
import com.example.core.data.ResultResponse
import com.example.core.data.SubCate
import com.example.core.local.converter.PostsConverter
import com.example.core.local.converter.SubCateConverter

@Database(
    entities = [Favorite::class, ResultResponse::class, SubCate::class, Posts::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [PostsConverter::class, SubCateConverter::class])
abstract class AppDataBase : RoomDatabase() {

    abstract fun appDataBaseDao(): AppDataBaseDao

}