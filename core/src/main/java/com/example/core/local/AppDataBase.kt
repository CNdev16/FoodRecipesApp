package com.example.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase(){

    abstract fun appDataBaseDao(): AppDataBaseDao

}